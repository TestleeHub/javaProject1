package service;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import model.Member;
import model.ProductMap;
import model.Wine;
import view.GuestView;
import view.HostView;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 고객 관련 서비스 제공
 * 				1. 로그인시 해당 id의 데이터 로드
 * 				2. 불러온 id의 장바구니 리스트, 구매확정 리스트(환불요청을 위한)를 추가 수정 삭제 조회 제공
 */

public class GuestImpl implements Guest {
	private static GuestImpl Instance = new GuestImpl();
	private ProductMap<Integer, Wine> cartMap;
	private ProductMap<Integer, Wine> buyConfirmMap;
	private static Scanner input;
	
	private LoginImpl loginService;
	private HostImpl hostService;
	private HostView hostView;
	private GuestView guestView;

	private GuestImpl() {
		cartMap = new ProductMap<Integer, Wine>();
		buyConfirmMap = new ProductMap<Integer, Wine>();
	}

	public static GuestImpl getInstance() {
		if (Instance == null)
			return new GuestImpl();
		else
			return Instance;
	}
	
	public void setInput(Scanner input) {
		GuestImpl.input = input;
	}
	
	public void setLoginService(LoginImpl loginService) {
		this.loginService = loginService;
	}
	
	public void setHostService(HostImpl hostService) {
		this.hostService = hostService;
	}
	
	public void setHostView(HostView hostView) {
		this.hostView = hostView;
	}
	
	public void setGuestView(GuestView guestView) {
		this.guestView = guestView;
	}
	
	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 로그인시 해당 id의 데이터를 guestService에 로딩
	 */
	@Override
	public void login(Member loginMember) {
		setCartMap(loginMember.getCartMap());
		setBuyConfirmMap(loginMember.getBuyConfirmMap());
	}
	
	public ProductMap<Integer, Wine> getCartMap() {
		return cartMap;
	}

	public ProductMap<Integer, Wine> getBuyConfirmMap() {
		return buyConfirmMap;
	}
	
	public void setCartMap(ProductMap<Integer, Wine> cartMap) {
		this.cartMap = cartMap;
	}
	
	public void setBuyConfirmMap(ProductMap<Integer, Wine> buyConfirmMap) {
		this.buyConfirmMap = buyConfirmMap;
	}

	public void addCartMap(Wine wine) throws Exception {
		if (cartMap.containsId(wine.getId()))
			throw new Exception("이미 제품 아이디가 존재 합니다.");
		else {
			cartMap.addProduct(wine.getId(), wine);
			LoginImpl.getInstance().writeFileMemberData();
		}
	}

	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 호스트가 결제 승인시 구매자와 일치하는 멤버 검색 후 해당 멤버의 BuyConfirmMap에 데이터 추가
	 * 			  2. 해당 멤버의 BuyConfirmMap에 이미 동일한 id의 제품이 있다면 수량만 더해줌
	 * 				
	 */
	public void addBuyConfirmMap(Wine wine) {
		ProductMap<Integer, Wine> confirmMap = LoginImpl.getInstance()
				.getMemberHashMap().getMemberMap()
				.get(wine.getBuyer())
				.getBuyConfirmMap();
		
		if (confirmMap.containsId(wine.getId())) {
			Wine targetWine = confirmMap.getProductHashMap().get(wine.getId());
			targetWine.setAmount(targetWine.getAmount() + wine.getAmount());
		} else {
			confirmMap.addProduct(wine.getId(), wine);
		}
		LoginImpl.getInstance().writeFileMemberData();
	}

	public void removeCartMap(int id) throws Exception {
		if (cartMap.containsId(id)) {
			cartMap.removeProduct(id);
			LoginImpl.getInstance().writeFileMemberData();
		} else
			throw new Exception("제품 아이디가 존재 하지 않습니다.");
	}

	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 호스트가 환불 승인시 구매자와 일치하는 멤버 검색 후 해당 멤버의 BuyConfirmMap에서 데이터 삭제
	 * 			  2. 모든 멤버를 조회하여 일치하는 제품id의 제품 모두 삭제
	 * 				
	 */
	public void removeBuyConfirmMap(int id) throws Exception {
		Map<String, Member> memberMap = LoginImpl.getInstance().getMemberHashMap().getMemberMap();
		Iterator<String> it = memberMap.keySet().iterator();
		
		int removeCount = 0;
		while(it.hasNext()) {
			String memberId = it.next();
			if(memberMap.get(memberId).getBuyConfirmMap().containsId(id)) {
				memberMap.get(memberId).getBuyConfirmMap().removeProduct(id);
				removeCount++;
			}
		}
		if(removeCount == 0) throw new Exception("제품 아이디가 존재 하지 않습니다.");
	}
	
	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 제품id와 수량을 입력받아 해당 하는 제품을 입력한 수량만큼 장바구니에 추가
	 * 			  2. 제품 목록에 입력받은 id와 일치하는 제품이 없을시 예외처리
	 */
	@Override
	public void addCart() {
		hostView.inventoryListView(hostService.getInventoryMap().getProductHashMap());
		while (true) {
			System.out.print("장바구니에 담을 제품의 번호를 입력하세요.[이전 : 0] : ");
			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				if (hostService.getInventoryMap().containsId(id) == false) {
					System.out.print("해당 번호의 제품이 없습니다. 다시 입력하세요 ");
					continue;
				} else {
					System.out.print("수량을 입력하세요 : ");
					int amount = input.nextInt();
					try {
						Wine wine = hostService.getInventoryMap().getProductHashMap().get(id);
						addCartMap(new Wine(wine.getId(), wine.getName(), wine.getOrigin(),
								wine.getPrice(), amount, loginService.getCurLoginId()));
						guestView.addCartSuccessView();
						return;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						continue;
					}
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
	 * 메서드 설명	: 1. 제품id를 입력받아 해당 id가 장바구니에 있으면 삭제
	 * 			  2. 장바구니 추가시 수량이 기존 제품리스트와 다르기 때문에 새로 new로 생성
	 */
	@Override
	public void deleteCart() {
		while (true) {
			System.out.print("삭제하려는 제품의 번호를 입력하세요.[이전 : 0] : ");
			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				try {
					removeCartMap(id);
					guestView.removeCartSuccessView();
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
	 * 메서드 설명	: 1. 제품id를 입력받아 장바구니에 해당제품이 있다면 그제품을 판매자의 구매요청 리스트에 추가, 장바구니에서 삭제
	 */
	@Override
	public void cartProductBuy() {
		guestView.CartListView(getCartMap().getProductHashMap());
		while (true) {
			System.out.print("구매할 제품의 번호를 입력하세요.[이전 : 0] : ");
			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				try {
					if (Optional.ofNullable(getCartMap().getProductHashMap().get(id)).isPresent()) {
						Wine wine = getCartMap().getProductHashMap().get(id);
						hostService.addBuyReqList(wine);
						guestView.BuyReqSuccessView();
						removeCartMap(id);
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

	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 제품id를 입력받아 제품리스트에서 바로 판매자의 구매요청 리스트에 추가
	 * 			  2. 바로구매시 수량이 기존 제품리스트와 다르기 때문에 새로 new로 생성
	 */
	@Override
	public void NowBuy() {
		while (true) {
			hostView.inventoryListView(hostService.getInventoryMap().getProductHashMap());
			System.out.print("구매할 제품의 번호를 입력하세요.[이전 : 0] : ");
			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				try {
					if (Optional.ofNullable(hostService.getInventoryMap().getProductHashMap().get(id)).isPresent()) {
						System.out.print("수량을 입력하세요 : ");
						int amount = input.nextInt();
						Wine wine = hostService.getInventoryMap().getProductHashMap().get(id);
						hostService.addBuyReqList(new Wine(wine.getId(), wine.getName(), wine.getOrigin(),
								wine.getPrice(), amount, loginService.getCurLoginId()));
						guestView.BuyReqSuccessView();
						return;
					} else {
						System.out.println("입력된 번호와 일치하는 제품이 없습니다.");
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

	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 제품id를 구매확정 리스트에서 삭제, 판매자의 환불 요청 리스트에 추가
	 */
	@Override
	public void Refund() {
		while (true) {
			guestView.buyConfirmListView(getBuyConfirmMap().getProductHashMap());
			System.out.print("환불 요청할 책의 번호를 입력하세요.[이전 : 0] : ");
			int id = 0;
			if (input.hasNextInt()) {
				if ((id = input.nextInt()) == 0)
					break;
				try {
					if (Optional.ofNullable(getBuyConfirmMap().getProductHashMap().get(id)).isPresent()) {
						Wine wine = getBuyConfirmMap().getProductHashMap().get(id);
						hostService.addRefundReqMap(wine);
						guestView.RefundReqSuccessView();
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
}
