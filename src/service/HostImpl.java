package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.ProductMap;
import model.Wine;
import model.productList;
import view.HostView;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 호스트 관련 서비스 클래스
 * 				1. 구매 확정
 * 				2. 구매 취소
 * 				3. 재고 리스트, 구매 요청 리스트, 환불 요청 리스트를 추가 수정 삭제 조회 기능 제공
 */

public class HostImpl implements Host {
	private static HostImpl Instance = new HostImpl();
	private static Scanner input;
	private ProductMap<Integer, Wine> inventoryMap;
	private productList<Wine> buyReqList;
	private ProductMap<Integer, Wine> refundReqMap;
	private HostView hostView;
	private GuestImpl guestService;
	private int paymentMoney;

	private HostImpl() {
		inventoryMap = new ProductMap<Integer, Wine>();
		buyReqList = new productList<Wine>();
		refundReqMap = new ProductMap<Integer, Wine>();
		paymentMoney = 0;
		try {
			readHostData();
		} catch (Exception e) {
			e.getMessage(); // 있으면 읽는것이기 때문에 파일 없음 예외시 아무것도 안함
		}
	}

	public static HostImpl getInstance() {
		if (Instance == null)
			return new HostImpl();
		else
			return Instance;
	}

	public void setInput(Scanner input) {
		HostImpl.input = input;
	}
	
	public void setHostView(HostView hostView) {
		this.hostView = hostView;
	}
	
	public void setGuestService(GuestImpl guestService) {
		this.guestService = guestService;
	}
	
	public ProductMap<Integer, Wine> getInventoryMap() {
		return inventoryMap;
	}

	public productList<Wine> getBuyReqList() {
		return buyReqList;
	}

	public ProductMap<Integer, Wine> getRefundReqMap() {
		return refundReqMap;
	}

	public int getPaymentMoney() {
		return paymentMoney;
	}

	public void setPaymentMoney(int paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public void addInventoryMap(Wine wine) throws Exception {
		if (inventoryMap.containsId(wine.getId()))
			throw new Exception("이미 제품 아이디가 존재 합니다.");
		else {
			inventoryMap.addProduct(wine.getId(), wine);
			updateHostData();
		}
	}

	public void addBuyReqList(Wine wine) {
		buyReqList.addProductList(wine);
		updateHostData();
	}

	public void addRefundReqMap(Wine wine) throws Exception {
		if (refundReqMap.containsId(wine.getId())) {

			Wine targetWine = refundReqMap.getProductHashMap().get(wine.getId());
			targetWine.setAmount(targetWine.getAmount() + wine.getAmount());
		} else
			refundReqMap.addProduct(wine.getId(), wine);

		updateHostData();
	}

	public void replaceInventoryMap(Wine wine) throws Exception {
		if (inventoryMap.containsId(wine.getId())) {
			inventoryMap.replaceProduct(wine.getId(), wine);
			updateHostData();
		} else
			throw new Exception("제품 아이디가 존재 하지 않습니다.");
	}

	public void removeBuyReqList(Wine wine) {
		buyReqList.removeProduct(wine);
		updateHostData();
	}

	public void removeInventoryMap(int id) throws Exception {
		if (inventoryMap.containsId(id)) {
			inventoryMap.removeProduct(id);
			updateHostData();
		} else
			throw new Exception("제품 아이디가 존재 하지 않습니다.");
	}

	public void removeRefundReqMap(int id) throws Exception {
		if (refundReqMap.containsId(id)) {
			refundReqMap.removeProduct(id);
			updateHostData();
		} else
			throw new Exception("제품 아이디가 존재 하지 않습니다.");
	}
	
	/*
	 * 작성자 : 이연우
	 * 작성일자 : 2023/07/10
	 * 메서드 설명 : 
	 * 		1. 결제를 승인하면 재고가 부족하지 않은지 체크 후 부족하지 않으면 재고 수량 계산 
	 * 		2. 결제가 승인된 건에 한하여 결산금액에 증가
	 */
	@Override
	public void orderConfirm(Wine orderWine) throws Exception {
		Wine target = inventoryMap.getProductHashMap().get(orderWine.getId());
		int preAmount = target.getAmount();
		int proAmount = preAmount - orderWine.getAmount();
		if (proAmount < 0)
			throw new Exception("재고가 부족하여 구매승인 불가합니다.");
		else {
			setPaymentMoney((orderWine.getAmount() * orderWine.getPrice()) + getPaymentMoney());
			target.setAmount(proAmount);
			updateHostData();
		}
	}

	/*
	 * 작성자 : 이연우 
	 * 작성일자 : 2023/07/10 
	 * 메서드 설명 : 
	 * 		1. 환불이 승인된 제품의 재고 수량 및 결산 금액 계산
	 */
	@Override
	public void orderCancle(Wine refundWine) {
		Wine target = inventoryMap.getProductHashMap().get(refundWine.getId());
		target.setAmount(target.getAmount() + refundWine.getAmount());
		setPaymentMoney(getPaymentMoney() - (refundWine.getAmount() * refundWine.getPrice()));
		updateHostData();
	}
	
	/*
	 * 작성자 : 이연우 
	 * 작성일자 : 2023/07/10 
	 * 메서드 설명 : 
	 * 		1. 구매 승인할 제품의 번호를 입력받아 해당하는 제품을 찾아서 리스트화
	 * 		2. 찾은 제품들을 구매요청 리스트에서 삭제
	 * 		3. 고객의 구매확정 리스트에 추가
	 */
	@Override
	public void orderConfirm() {
		while (true) {
			System.out.print("구매승인할 제품의 번호를 입력하세요.[이전 : 0] : ");

			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				else {
					int idForLamda = id;
					Optional.ofNullable(getBuyReqList().getProductList().stream()
							.filter(product -> product.getId() == idForLamda).collect(Collectors.toList()))
					.ifPresent(product -> {
						for(Wine wine : product) {
							try {
								removeBuyReqList(wine);
								orderConfirm(wine);
							} catch (Exception e) {
								System.out.println(e.getMessage());
								return;
							}
							guestService.addBuyConfirmMap(wine);
							hostView.orderConfirmSuccessView();
						}
					});
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				input = new Scanner(System.in);
				continue;
			}
		}
	}

	/*
	 * 작성자 : 이연우 
	 * 작성일자 : 2023/07/10 
	 * 메서드 설명 : 
	 * 		1. 환불 처리할 제품의 번호를 입력받아 해당하는 제품을 찾아서 환불 요청 리스트에서 삭제
	 * 		2. 고객의 구매확정 리스트에서 삭제
	 */
	@Override
	public void orderCancle() {
		while (true) {
			System.out.print("환불처리 할 제품의 번호를 입력하세요.[이전 : 0] : ");
			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				try {
					if (Optional.ofNullable(getRefundReqMap().getProductHashMap().get(id)).isPresent()) {
						orderCancle(getRefundReqMap().getProductHashMap().get(id));
						removeRefundReqMap(id);
						guestService.removeBuyConfirmMap(id);
						hostView.orderCancleSuccessView();
						return;
					} else {
						System.out.println("입력된 번호와 일치하는 제품이 없습니다. 다시 입력 하세요");
						continue;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				input = new Scanner(System.in);
				continue;
			}
		}
	}

	@Override
	public void addInventory() {
		hostView.addInventoryView();
		while (true) {
			System.out.print("와인 이름 : ");
			String name = input.next();
			System.out.print("와인 원산지 : ");
			String origin = input.next();
			System.out.print("와인 가격 : ");
			int price = input.nextInt();
			System.out.print("와인 수량 : ");
			int amount = input.nextInt();
			try {
				addInventoryMap(new Wine(getRandomProductId(), name, origin, price, amount));
				hostView.addInventorySuccessView();
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}

	@Override
	public void replaceInventory() {
		while (true) {
			System.out.print("수정하려는 제품의 코드번호를 입력하세요.[이전 : 0] : ");
			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				if (getInventoryMap().containsId(id) == false) {
					System.out.println("해당 번호의 제품이 없습니다. 다시 입력하세요 ");
					continue;
				} else {
					while (true) {
						hostView.replaceInventoryView();
						System.out.print("와인 이름 : ");
						String name = input.next();
						System.out.print("와인 원산지 : ");
						String origin = input.next();
						System.out.print("와인 가격 : ");
						int price = input.nextInt();
						System.out.print("와인 수량 : ");
						int amount = input.nextInt();
						try {
							replaceInventoryMap(new Wine(id, name, origin, price, amount));
							hostView.replaceInventorySuccessView();
							return;
						} catch (Exception e) {
							System.out.println(e.getMessage());
							continue;
						}
					}
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				input = new Scanner(System.in);
				continue;
			}
		}
	}

	@Override
	public void deleteInventory() {
		while (true) {
			System.out.print("삭제하려는 제품의 코드번호를 입력하세요.[이전 : 0] : ");
			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				try {
					removeInventoryMap(id);
					hostView.deleteInventorySuccessView(id);
					return;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				input = new Scanner(System.in);
				continue;
			}
		}
	}
	
	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 제품 생성시 key 값을 랜덤 생성
	 * 			  2. 중복된 key가 생성시 재시도
	 */
	public int getRandomProductId() {
		int randomId;
		while (true) {
			randomId = (int) (Math.random() * 1000) + 1000;
			if (getInventoryMap().containsId(randomId))
				continue;
			else
				break;
		}
		return randomId;
	}

	/*
	 * 작성자 : 이연우 작성일자 : 2023/07/10 메서드 설명 : 1. 관리자서비스의 멤버변수를 직렬화하여 파일입출력
	 */
	@SuppressWarnings("unchecked")
	public void readHostData() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("HostData.txt");
		;
		ObjectInputStream ois = new ObjectInputStream(fis);
		inventoryMap = (ProductMap<Integer, Wine>) ois.readObject();
		buyReqList = (productList<Wine>) ois.readObject();
		refundReqMap = (ProductMap<Integer, Wine>) ois.readObject();
		paymentMoney = (int) ois.readObject();
		if (fis != null)
			fis.close();
		if (ois != null)
			ois.close();
	}

	public void updateHostData() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("HostData.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(inventoryMap);
			oos.writeObject(buyReqList);
			oos.writeObject(refundReqMap);
			oos.writeObject(paymentMoney);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
