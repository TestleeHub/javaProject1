package service;

import model.Wine;

public interface Host {

	public static final String ID = "host"; // 관리자 아이디
	public static final String PASSWORD = "host1234"; // 관리자 비밀번호

	// Host - 재고관리
	public void productList(); // 상품 목록

	public void productAdd(Wine wine); // 상품 추가

	public void productUpdate(); // 상품 수정

	public void productRemove(); // 상품 삭제

	// Host - 주문관리
	public void orderList(); // 주문 목록

	public void orderConfirm(); // 결제 승인

	public void orderCancle(); // 결제 취소

	// Host - 결산
	public void saleTotal(); // 결산
}
