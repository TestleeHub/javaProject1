package view;

import java.util.Iterator;
import java.util.Map;

import model.Wine;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 고객 메뉴 관련 콘솔 화면 
 */

public class GuestView {
	public GuestView() {	}
	
	public void GuestMainMenuView() {
		System.out.println("----------------고객 메뉴----------------");
		System.out.println("  1.장바구니   2.구매   3. 환불  4.로그아웃   ");
		System.out.println("----------------------------------------");
		System.out.print("메뉴 번호를 입력하세요 : ");
	}

	public void guestCartMenuView() {
		System.out.println("-----------------장바구니-----------------");
		System.out.println("    1.목록  2.추가  3.구매  4.삭제  5.이전    ");
		System.out.println("----------------------------------------");
		System.out.print("메뉴 번호를 입력하세요 : ");
	}

	public void CartListView(Map<Integer, Wine> CartListMap) {
		System.out.println("============장바구니 목록============");
		System.out.println("번호    제품명    원산지    가격    수량");
		System.out.println("==================================");
		Iterator<Integer> it = CartListMap.keySet().iterator();
		while(it.hasNext()) {
			int id = it.next();
			System.out.println(CartListMap.get(id));
		}
	}

	public void addCartView() {
		System.out.println("============장바구니 추가============");
	}

	public void addCartSuccessView() {
		System.out.println("==================================");
		System.out.println("        장바구니에 담겼습니다.         ");
		System.out.println("==================================");
	}

	public void removeCartSuccessView() {
		System.out.println("==================================");
		System.out.println("       목록에서 삭제되었습니다.        ");
		System.out.println("==================================");
	}

	public void BuyReqSuccessView() {
		System.out.println("==================================");
		System.out.println("          구매 요청되었습니다.        ");
		System.out.println("==================================");
	}

	public void buyConfirmListView(Map<Integer, Wine> buyConfirmListMap) {
		System.out.println("============구매완료 목록============");
		System.out.println("번호    제품명    원산지    가격    수량");
		System.out.println("==================================");
		Iterator<Integer> it = buyConfirmListMap.keySet().iterator();
		while(it.hasNext()) {
			int id = it.next();
			System.out.println(buyConfirmListMap.get(id));
		}
	}
	
	public void RefundReqSuccessView() {
		System.out.println("==================================");
		System.out.println("          환불 요청되었습니다.        ");
		System.out.println("==================================");
	}
	
}
