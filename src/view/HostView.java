package view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Wine;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 관리자 메뉴 관련 콘솔 화면
 */

public class HostView {
	public HostView() {
	}
	
	public void hostMainMenuView() {
		System.out.println("----------------관리자 메뉴----------------");
		System.out.println("    1.재고관리     2.주문관리     3.로그아웃    ");
		System.out.println("----------------------------------------");
		System.out.print("메뉴 번호를 입력하세요 : ");
	}
	
	public void hostInventoryMenuView() {
		System.out.println("-----------------재고관리-----------------");
		System.out.println("    1.목록  2.추가  3.수정  4.삭제  5.이전    ");
		System.out.println("----------------------------------------");
		System.out.print("메뉴 번호를 입력하세요 : ");
	}
	
	public void hostOrderMenuView() {
		System.out.println("-----------------주문관리-----------------");
		System.out.println("1.주문목록  2.결제승인  3.결제취소  4.결산  5.이전");
		System.out.println("----------------------------------------");
		System.out.print("메뉴 번호를 입력하세요 : ");
	}
	
	public void addInventoryView() {
		System.out.println("==============제품 등록==============");
	}
	
	public void addInventorySuccessView() {
		System.out.println("==================================");
		System.out.println("          제품이 등록되었습니다.         ");
		System.out.println("==================================");
	}
	
	public void replaceInventoryView() {
		System.out.println("==============제품 수정==============");
	}
	
	public void replaceInventorySuccessView() {
		System.out.println("==================================");
		System.out.println("          제품이 수정되었습니다.         ");
		System.out.println("==================================");
	}

	public void inventoryListView(Map<Integer, Wine> inventoryListMap) {
		System.out.println("==============제품 목록==============");
		System.out.println("번호    제품명    원산지    가격    수량");
		System.out.println("==================================");
		Iterator<Integer> it = inventoryListMap.keySet().iterator();
		while(it.hasNext()) {
			int id = it.next();
			System.out.println(inventoryListMap.get(id));
		}
	}

	public void deleteInventorySuccessView(int id) {
		System.out.println("==================================");
		System.out.println("     "+id+"번 제품이 삭제되었습니다.    ");
		System.out.println("==================================");
	}

	public void buyReqListView(List<Wine> buyReqList) {
		System.out.println("==============제품 목록==============");
		System.out.println("번호    제품명    원산지    가격    수량     구매자");
		System.out.println("==================================");
		
		for(Wine wine : buyReqList) {
			System.out.println(wine + "     " +wine.getBuyer());
		}
	}
	
	public void orderConfirmSuccessView() {
		System.out.println("==================================");
		System.out.println("          결제 승인되었습니다.         ");
		System.out.println("==================================");
	}

	public void evalueatePaymentView(int paymentMoney) {
		System.out.println("결산 : " + paymentMoney + "원");
	}
	
	public void refundReqListView(Map<Integer, Wine> refundReqListMap) {
		System.out.println("==============제품 목록==============");
		System.out.println("번호    제품명    원산지    가격    수량");
		System.out.println("==================================");
		Iterator<Integer> it = refundReqListMap.keySet().iterator();
		while(it.hasNext()) {
			int id = it.next();
			System.out.println(refundReqListMap.get(id));
		}
	}
	
	public void orderCancleSuccessView() {
		System.out.println("==================================");
		System.out.println("          환불 처리되었습니다.         ");
		System.out.println("==================================");
	}
}
