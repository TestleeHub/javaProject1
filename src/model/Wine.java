package model;

import java.io.Serializable;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 판매할 Wine의 데이터 클래스
 */

// DTO(Data Transfer Object)
public class Wine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 멤버변수
	private int id;
	private String name;
	private String origin;
	private int price;
	private int amount;
	private String Buyer;

	// 생성자
	public Wine() {	}

	public Wine(int id, String name, String origin, int price, int amount) {
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.price = price;
		this.amount = amount;
	}
	
	public Wine(int id, String name, String origin, int price, int amount, String Buyer) {
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.price = price;
		this.amount = amount;
		this.Buyer = Buyer;
	}

	// 멤버 메서드
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getBuyer() {
		return Buyer;
	}
	
	public void setBuyer(String buyer) {
		Buyer = buyer;
	}
	
	@Override
	public String toString() {
		return id + "\t" + name + "\t" + origin + "\t" + price + "\t" + amount;
	}
}
