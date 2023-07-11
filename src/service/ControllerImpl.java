package service;

import java.util.Scanner;

import model.Code;
import view.GuestView;
import view.HostView;
import view.LoginView;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 실행 후 Input 요청을 받아 가공하여 Login, Guest, Host 서비스에 전달, 
 * 				1. 로그인 메뉴, 고객 메뉴, 관리자 메뉴, 재고관리 메뉴, 주문관리 메뉴 기능 제공
 */

public class ControllerImpl implements Controller {
	public static Scanner input = new Scanner(System.in);
	private static ControllerImpl Instance = new ControllerImpl();

	private LoginImpl loginService;
	private LoginView loginView;
	
	private HostImpl hostService;
	private HostView hostView;
	private GuestImpl guestService;
	private GuestView guestView;

	public ControllerImpl() {
		loginService = LoginImpl.getInstance();
		loginView = new LoginView();
		hostService = HostImpl.getInstance();
		hostView = new HostView();
		guestService = GuestImpl.getInstance();
		guestView = new GuestView();
		
		loginService.setInput(input);
		hostService.setInput(input);
		guestService.setInput(input);
		
		loginService.setLoginView(loginView);
		
		hostService.setGuestService(guestService);
		hostService.setHostView(hostView);
		
		guestService.setGuestView(guestView);
		guestService.setHostService(hostService);
		guestService.setHostView(hostView);
		guestService.setLoginService(loginService);
	}

	public static ControllerImpl getInstance() {
		if (Instance == null)
			return new ControllerImpl();
		else
			return Instance;
	}
	
	public Scanner getInput() {
		return input;
	}

	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 최초 호출 메서드
	 * 			  2. 관리자 로그인, 고객 로그인, 회원가입, 프로그램 종료 제공
	 */
	@Override
	public void loginService() {
		boolean condition = true;
		while (condition) {
			loginView.loginMainMenuView();

			if (input.hasNextInt()) {
				int loginMenuRequest = input.nextInt() + Code.LOGIN_MENU;

				switch (loginMenuRequest) {
				case Code.LOGIN_HOSTLOGIN:
					loginService.hostLogin();
					hostService();
					break;
				case Code.LOGIN_GUESTLOGIN:
					loginService.guestLogin();
					guestService();
					break;
				case Code.LOGIN_GUESTJOIN:
					loginService.join();
					break;
				case Code.LOGIN_MENU_EXIT:
					condition = false;
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
					break;
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				input = new Scanner(System.in);
				continue;
			}
		}
		input.close();
	}

	/*
	 * 작성자 	: 이연우
	 * 작성일자 	: 2023/07/10
	 * 메서드 설명	: 1. 관리자 관련 서비스 호출
	 * 			  2. 재고 관리, 주문관리, 관리자 로그아웃 제공
	 */
	@Override
	public void hostService() {
		boolean condition = true;
		while (condition) {
			hostView.hostMainMenuView();
			if (input.hasNextInt()) {
				int hostMenuRequest = input.nextInt() + Code.HOST_MENU;

				switch (hostMenuRequest) {
				case Code.HOST_INVENTORY_MANAGEMENT_MENU:
					InventoryManagement();
					break;
				case Code.HOST_ORDER_MANAGEMENT_MENU:
					orderManagement();
					break;
				case Code.HOST_LOGOUT:
					condition = false;
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
					break;
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
	 * 메서드 설명	: 1. 고객 관련 서비스 호출
	 * 			  2. 장바구니 관리, 바로 구매, 환불, 고객 로그아웃 제공
	 */
	@Override
	public void guestService() {
		boolean condition = true;
		while (condition) {
			guestView.GuestMainMenuView();

			if (input.hasNextInt()) {
				int guestMenuRequest = input.nextInt() + Code.GUEST_MENU;

				switch (guestMenuRequest) {
				case Code.GUEST_CART_MANAGEMENT_MENU:
					cartManagement();
					break;
				case Code.GUEST_NOW_BUY_MENU:
					guestService.NowBuy();
					break;
				case Code.GUEST_REFUND_MENU:
					guestService.Refund();
					break;
				case Code.GUEST_LOGOUT:
					condition = false;
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
					break;
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
	 * 메서드 설명	: 1. 재고 조회, 재고 추가, 재고 수정, 제고 삭제 기능 제공
	 */
	@Override
	public void InventoryManagement() {
		boolean condition = true;
		while (condition) {
			hostView.hostInventoryMenuView();

			if (input.hasNextInt()) {
				int stockMenuRequest = input.nextInt() + Code.HOST_INVENTORY_MANAGEMENT;

				switch (stockMenuRequest) {
				case Code.HOST_INVENTORY_LIST:
					hostView.inventoryListView(hostService.getInventoryMap().getProductHashMap());
					break;
				case Code.HOST_INVENTORY_ADD:
					hostService.addInventory();
					break;
				case Code.HOST_INVENTORY_REPLACE:
					hostService.replaceInventory();
					break;
				case Code.HOST_INVENTORY_DEL:
					hostService.deleteInventory();
					break;
				case Code.HOST_INVENTORY_MANAGEMENT_EXIT:
					condition = false;
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
					break;
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
	 * 메서드 설명	: 1. 주문 조회, 주문 결제, 주문 취소, 결산 기능 제공
	 */
	@Override
	public void orderManagement() {
		boolean condition = true;
		while (condition) {
			hostView.hostOrderMenuView();

			if (input.hasNextInt()) {
				int CartMenuRequest = input.nextInt() + Code.HOST_ORDER_MANAGEMENT;

				switch (CartMenuRequest) {
				case Code.HOST_ORDER_BUYREQ_LIST:
					hostView.buyReqListView(hostService.getBuyReqList().getProductList());
					break;
				case Code.HOST_ORDER_CONFIRML:
					hostService.orderConfirm();
					break;
				case Code.HOST_ORDER_CALCLE:
					hostView.refundReqListView(hostService.getRefundReqMap().getProductHashMap());
					hostService.orderCancle();
					break;
				case Code.HOST_ORDER_EVALUEATE:
					hostView.evalueatePaymentView(hostService.getPaymentMoney());
					break;
				case Code.HOST_ORDER_MANAGEMENT_EXIT:
					condition = false;
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
					break;
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
	 * 메서드 설명	: 1. 장바구니 조회, 장바구니 추가, 장바구니 삭제, 장바구니 결제 기능 제공
	 */
	@Override
	public void cartManagement() {
		boolean condition = true;
		while (condition) {
			guestView.guestCartMenuView();

			if (input.hasNextInt()) {
				int CartMenuRequest = input.nextInt() + Code.GUEST_CART_MANAGEMENT;

				switch (CartMenuRequest) {
				case Code.GUEST_CART_LIST:
					guestView.CartListView(guestService.getCartMap().getProductHashMap());
					break;
				case Code.GUEST_CART_ADD:
					guestView.addCartView();
					guestService.addCart();
					break;
				case Code.GUEST_CART_BUY:
					guestService.cartProductBuy();
					break;
				case Code.GUEST_CART_DEL:
					guestService.deleteCart();
					break;
				case Code.GUEST_CART_MANAGEMENT_EXIT:
					condition = false;
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
					break;
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				input = new Scanner(System.in);
				continue;
			}
		}
	}
}
