package view;

import java.util.Scanner;

/*
 * 작성자 : 이연우
 * 작성일자 : 2023/07/03
 * 클래스 설명 : Menu....
 */

public class MenuImpl {
	public static Scanner input = new Scanner(System.in);
	private static MenuImpl Instance = new MenuImpl();
	
	private MenuImpl() {	}
	
	public static MenuImpl getInstance() {
		if(Instance == null) return new MenuImpl();
		else return Instance;
	}
	
	public void loginMainMenuView() {
		System.out.println("------------------로그인------------------");
		System.out.println("    1.관리자    2.고객   3.회원가입   4.종료   ");
		System.out.println("----------------------------------------");
		System.out.print("메뉴 번호를 입력하세요 : ");
	}
	
	public void joinView() {
		System.out.println("------------------로그인------------------");
		System.out.println("                 회원가입                  ");
		System.out.println("----------------------------------------");
	}
	
	public void joinSuccessView() {
		System.out.println("------------------로그인------------------");
		System.out.println("               회원가입 완료                ");
		System.out.println("----------------------------------------");
	}
	
	public void joinFailView() {
		System.out.println("------------------로그인------------------");
		System.out.println("               회원가입 실패                ");
		System.out.println("----------------------------------------");
	}

	public void hostMainMenuView() {
		System.out.println("----------------관리자 메뉴----------------");
		System.out.println("    1.재고관리     2.주문관리     3.로그아웃    ");
		System.out.println("----------------------------------------");
	}
	
	public void hostStockMenuView() {
		System.out.println("-----------------재고관리-----------------");
		System.out.println("    1.목록  2.추가  3.수정  4.삭제  5.이전    ");
		System.out.println("----------------------------------------");
	}
	
	public void hostOrderMenuView() {
		System.out.println("-----------------주문관리-----------------");
		System.out.println("1.주문목록  2.결제승인  3.결제취소  4.결산  5.이전");
		System.out.println("----------------------------------------");
	}
	
	public void registProductView() {
		System.out.println("==============제품 등록==============");
	}
	
	public void registProductSuccessView() {
		System.out.println("==================================");
		System.out.println("          제품이 등록되었습니다.         ");
		System.out.println("==================================");
	}
}
