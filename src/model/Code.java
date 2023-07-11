package model;

// 메뉴 코드
public interface Code {
	// 로그인 메뉴 코드
	static final int LOGIN_MENU = 1000; 			// 로그인 메뉴
	static final int LOGIN_HOSTLOGIN = 1001; 		// 관리자 로그인
	static final int LOGIN_GUESTLOGIN = 1002; 		// 고객 로그인
	static final int LOGIN_GUESTJOIN = 1003; 		// 고객 회원가임
	static final int LOGIN_MENU_EXIT = 1004;		// 로그인 종료

	// 관리자 메뉴 코드
	static final int HOST_MENU = 100;				// 관리자 메뉴
	static final int HOST_INVENTORY_MANAGEMENT_MENU = 101;		// 재고 관리
	static final int HOST_ORDER_MANAGEMENT_MENU = 102;			// 주문 관리
	static final int HOST_LOGOUT = 103;				// 관리자 로그아웃
	
	// 관리자 재고관리
	static final int HOST_INVENTORY_MANAGEMENT = 110;	// 재고관리 메뉴
	static final int HOST_INVENTORY_LIST = 111; 		// 재고 목록
	static final int HOST_INVENTORY_ADD = 112; 			// 상품 추가
	static final int HOST_INVENTORY_REPLACE = 113; 		// 상품 수정
	static final int HOST_INVENTORY_DEL = 114; 			// 상품 삭제
	static final int HOST_INVENTORY_MANAGEMENT_EXIT = 115; // 재고관리 메뉴 종료
	
	// 관리자 주문관리
	static final int HOST_ORDER_MANAGEMENT = 120;	// 주문관리 메뉴
	static final int HOST_ORDER_BUYREQ_LIST = 121;	// 구매요청 목록
	static final int HOST_ORDER_CONFIRML = 122;		// 주문 확정 처리
	static final int HOST_ORDER_CALCLE = 123;		// 주문 취소
	static final int HOST_ORDER_EVALUEATE = 124;	// 관리자 결산
	static final int HOST_ORDER_MANAGEMENT_EXIT = 125;	// 관리자 메뉴 종료
	
	// 고객 메뉴 코드
	static final int GUEST_MENU = 200;					// 고객 메뉴
	static final int GUEST_CART_MANAGEMENT_MENU = 201;	// 장바구니 
	static final int GUEST_NOW_BUY_MENU = 202;			// 바로구매
	static final int GUEST_REFUND_MENU = 203;			// 환불
	static final int GUEST_LOGOUT = 204;				// 고객 로그아웃
	
	// 고객 장바구니 관리
	static final int GUEST_CART_MANAGEMENT= 210;	// 장바구니 메뉴
	static final int GUEST_CART_LIST = 211;			// 장바구니 리스트
	static final int GUEST_CART_ADD = 212;			// 장바구니 추가
	static final int GUEST_CART_BUY = 213;			// 장바구니 구매
	static final int GUEST_CART_DEL = 214;			// 장바구니 삭제
	static final int GUEST_CART_MANAGEMENT_EXIT = 215; // 이전
}
