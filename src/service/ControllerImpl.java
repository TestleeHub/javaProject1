package service;

import java.util.Scanner;

import model.Code;
import model.Member;
import model.Wine;
import view.MenuImpl;

public class ControllerImpl implements Controller {
	private GuestImpl guestService;
	private HostImpl hostService;
	private LoginImpl loginService;
	private MenuImpl Oputview;
	
	public static Scanner input = new Scanner(System.in);
	private static ControllerImpl Instance = new ControllerImpl();
	
	public ControllerImpl() {
//		guestService = GuestImpl.getInstance();
		hostService = HostImpl.getInstance();
		loginService = LoginImpl.getInstance();
		Oputview = MenuImpl.getInstance();
	}
	
	public static ControllerImpl getInstance() {
		if(Instance == null) return new ControllerImpl();
		else return Instance;
	}

	@Override
	public void loginService() {
		boolean condition = true;
		while(condition) {
			Oputview.loginMainMenuView();
			
			int loginMenuRequest = input.nextInt() + Code.SHOP_LOGIN_MENU;
			
			switch (loginMenuRequest) {
			case Code.SHOP_LOGIN_HOSTLOGIN:
				hostLogin();
				hostService();
				break;
			case Code.SHOP_LOGIN_GUESTLOGIN:
				guestLogin();
				guestService();
				break;
			case Code.SHOP_LOGIN_GUESTJOIN:
				Oputview.joinView();
				join();
				Oputview.joinSuccessView();
				break;
			case Code.SHOP_LOGIN_MENU_EXIT:
				condition = false;
				break;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				break;
			}
		}
	}

	@Override
	public void guestService() {
		
	}

	@Override
	public void hostService() {
		boolean condition = true;
		while(condition) {
			Oputview.hostMainMenuView();
			
			int hostMenuRequest = input.nextInt() + Code.ADMIN_MENU;
			
			switch (hostMenuRequest) {
			case Code.ADMIN_STOCK_MENU:
				stockManagement();
				break;
			case Code.ADMIN_ORDER_MENU:
				orderManagement();
				guestLogin();
				break;
			case Code.ADMIN_LOGOUT:
				condition = false;
				break;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				break;
			}
		}
	}
	
	private void orderManagement() {
		
	}

	private void stockManagement() {

		boolean condition = true;
		while(condition) {
			Oputview.hostStockMenuView();
			
			int stockMenuRequest = input.nextInt() + Code.ADMIN_STOCK_MANAGEMENT;
			
			switch (stockMenuRequest) {
			case Code.ADMIN_GOODS_LIST:
				break;
			case Code.ADMIN_GOODS_ADD:
				Oputview.registProductView();
				registProduct();
				break;
			case Code.ADMIN_GOODS_UPDATE:
				break;
			case Code.ADMIN_GOODS_DEL:
				break;
			case Code.ADMIN_STOCK_MANAGEMENT_EXIT:
				condition = false;
				break;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력 하십시오.");
				break;
			}
		}
	
	}

	private void registProduct() {
		while(true) {
			System.out.print("와인 이름 : ");
			String name = input.next();
			System.out.print("와인 원산지 : ");
			String origin = input.next();
			System.out.print("와인 가격 : ");
			int price = input.nextInt();
			System.out.print("와인 수량 : ");
			int amount = input.nextInt();
			try {
				hostService.productAdd(new Wine(getRandomProductId(), name, origin, price, amount));;
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	
	}

	public void join() {
		while(true) {
			System.out.print("ID : ");
			String id = input.next();
			System.out.print("PW : ");
			String password = input.next();
			try {
				loginService.join(new Member(id, password));
				return;
			} catch (Exception e) {
				Oputview.joinFailView();
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
	public void hostLogin() {
		while(true) {
			System.out.print("관리자 ID : ");
			String id = input.next();
			System.out.print("관리자 PW : ");
			String password = input.next();
			try {
				loginService.hostLogin(new Member(id, password));
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
	public void guestLogin() {
		while(true) {
			System.out.print("회원 ID : ");
			String id = input.next();
			System.out.print("회원 PW : ");
			String password = input.next();
			try {
				loginService.guestLogin(new Member(id, password));
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
	public int getRandomProductId() {
		int randomId;
		while(true) {
			randomId = (int)(Math.random() * 1000) + 1000;
			if(hostService.checkDupProductId(randomId))
				continue;
			else
				break;
		}
		return randomId;
	}
}
