package view;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 로그인 관련 콘솔 화면
 */

public class LoginView {
	
	public LoginView() {	}

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
}
