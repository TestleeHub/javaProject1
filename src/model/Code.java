package model;

// 메뉴 코드
public interface Code {
	// 상수
	static final int SHOP_LOGIN = 999;// 로그인
	static final int EXIT = 1;// 시스템 종료
	
	// Login
	static final int SHOP_LOGIN_MENU = 1000;
	static final int SHOP_LOGIN_HOSTLOGIN = 1001;
	static final int SHOP_LOGIN_GUESTLOGIN = 1002;
	static final int SHOP_LOGIN_GUESTJOIN = 1003;
	static final int SHOP_LOGIN_MENU_EXIT = 1004;

	// Host
	static final int ADMIN_MENU = 100;// Host
	static final int ADMIN_STOCK_MENU = 101;// 재고관리
	static final int ADMIN_ORDER_MENU = 102;
	static final int ADMIN_LOGOUT = 103;
	
	// Host 재고관리
	
	static final int ADMIN_STOCK_MANAGEMENT = 110;// 재고관리
	static final int ADMIN_GOODS_LIST = 111; // 상품목록
	static final int ADMIN_GOODS_ADD = 112; // 상품추가
	static final int ADMIN_GOODS_UPDATE = 113; // 상품수정
	static final int ADMIN_GOODS_DEL = 114; // 상품삭제
	static final int ADMIN_STOCK_MANAGEMENT_EXIT = 115; // 상품삭제
	
	
//	static final int ADMIN_MENU_LOGIN = 199;
//
//	// Host 재고관리
//	static final int ADMIN_STOCK_MENU = 110;// 재고관리
//	static final int ADMIN_GOODS_LIST = 111; // 상품목록
//	static final int ADMIN_GOODS_ADD = 112; // 상품추가
//	static final int ADMIN_GOODS_UPDATE = 113; // 상품수정
//	static final int ADMIN_GOODS_DEL = 199; // 상품삭제
//
//	// Host 주문관리
//	static final int ADMIN_ORDER_MENU = 120; // 상품삭제
//	static final int ADMIN_ORDER_LIST = 121; // 상품삭제
//
//	// Host 결제기능
//	static final int ADMIN_ORDER_CONFIRM = 122; // 결제 승인
//	static final int ADMIN_ORDER_CANCEL = 122; // 결제 취소
//
//	// Host 결산
//	static final int ADMIN_ORDER_TOTAL = 130; // 결제 취소
//	
//	//-----------------------------------------------
//	
//	//Guest
//	static final int GUEST_MENU = 200;// 고객 
//	static final int GUEST_JOIN = 298;// 고객 회원가입
//	static final int GUEST_LOGIN = 299;// 고객 로그인
//	
//	//Guest - 상품목록
//	static final int GUEST_GOODS = 210;// 상품 리스트
//	
//	static final int GUEST_CART_LIST = 210;// 장바구니 리스트
//	static final int GUEST_CART_ADD = 221;// 장바구니 추가
//	static final int GUEST_CART_DEL = 222;// 장바구니 삭제
//	static final int GUEST_CART_BUY = 223;// 장바구니 구매
//	
//	//Guest - 바로구매
//	static final int GUEST_NOW_BUY = 230;// 장바구니 구매
//	
//	//Guest - 환불
//	static final int GUEST_REFUND = 240;// 장바구니 구매
}
