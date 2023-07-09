package service;

import model.ProductHashMap;
import model.Wine;

public class HostImpl implements Host {
	private static HostImpl Instance = new HostImpl();
	ProductHashMap productHashMap;

	private HostImpl() {	
		productHashMap = new ProductHashMap();
	}

	public static HostImpl getInstance() {
		if (Instance == null)
			return new HostImpl();
		else
			return Instance;
	}
	@Override
	public void productList() {
		
	}

	@Override
	public void productAdd(Wine wine) {
		
	}

	@Override
	public void productUpdate() {
		
	}

	@Override
	public void productRemove() {
		
	}

	@Override
	public void orderList() {
		
	}

	@Override
	public void orderConfirm() {
		
	}

	@Override
	public void orderCancle() {
		
	}

	@Override
	public void saleTotal() {
		
	}
	
	public boolean checkDupProductId(int id) {
		return productHashMap.containsId(id);
	}
	//접근시 싱글톤을 이용한다.
}
