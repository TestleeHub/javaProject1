package view;

import service.ControllerImpl;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 1.메인 함수 호출
 * 			: 2.컨트롤러의 로그인 서비스 호출
 */
public class Main {
	public static void main(String[] args) {
		// 로그인 호출
		ControllerImpl.getInstance().loginService();
	}
}
