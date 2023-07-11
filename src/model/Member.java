package model;

import java.io.Serializable;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 고객의 데이터 클래스
 * 				1. 고객 개별의 id, password, 장바구니 리스트, 구매확정 리스트 제공
 */

public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ProductMap<Integer, Wine> cartMap;
	private ProductMap<Integer, Wine> buyConfirmMap;
	private String id;
	private String password;
	
	public Member(String id, String password) throws Exception {
		if(id.length() > 20 || id.length() < 4) throw new Exception("아이디는 4~20자 입니다. 다시 입력하세요");
		if(password.length() < 4) throw new Exception("암호는 4자 이상이어야 합니다. 다시 입력하세요");
		this.id = id;
		this.password = password;
		cartMap = new ProductMap<Integer, Wine>();
		buyConfirmMap = new ProductMap<Integer, Wine>();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ProductMap<Integer, Wine> getCartMap() {
		return cartMap;
	}
	
	public void setCartMap(ProductMap<Integer, Wine> cartMap) {
		this.cartMap = cartMap;
	}
	
	public ProductMap<Integer, Wine> getBuyConfirmMap() {
		return buyConfirmMap;
	}
	
	public void setBuyConfirmMap(ProductMap<Integer, Wine> buyConfirmMap) {
		this.buyConfirmMap = buyConfirmMap;
	}
}
