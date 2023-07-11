package service;

import model.Wine;

public interface Host {

	public static final String ID = "host"; // 관리자 아이디
	public static final String PASSWORD = "host1234"; // 관리자 비밀번호
	
	public void orderConfirm(Wine orderWine) throws Exception;

	public void orderCancle(Wine refundWine) ;

	void deleteInventory();

	void replaceInventory();

	void addInventory();

	void orderCancle();

	void orderConfirm();
}
