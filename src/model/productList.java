package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 제품을 List으로 데이터화
 * 				1. 제품의 List의 추가 수정 삭제 조회 기능 제공
 */
public class productList<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> productList;
	
	public productList() {
		productList = new ArrayList<T>();
	}
	
	public void addProductList(T product) {
		productList.add(product);
	}
	
	public void removeProduct(T product) {
		productList.remove(product);
	}
	
	public void removeProduct(int indext) {
		productList.remove(indext);
	}
	
	public List<T> getProductList() {
		return productList;
	}
}
