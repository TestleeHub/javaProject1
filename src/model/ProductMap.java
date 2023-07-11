package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 제품을 Map으로 데이터화
 * 				1. 제품의 Map의 추가 수정 삭제 조회 기능 제공
 */

public class ProductMap<K,V> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<K, V> productMap;
	
	public ProductMap() {
		productMap = new HashMap<K, V>();
	}
	
	public void addProduct(K key, V value) {
		productMap.put(key, value);
	}
	
	public void replaceProduct(K key, V value) {
		productMap.replace(key, value);
	}
	
	public void removeProduct(K key) {
		productMap.remove(key);
	}
	
	public boolean containsId(K key) {
		return productMap.containsKey(key);
	}
	
	public Map<K, V> getProductHashMap() {
		return productMap;
	}
}
