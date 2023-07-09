package model;

// DTO(Data Transfer Object)
public class Wine {
	// 멤버변수
	private int id;
	private String name;
	private String origin;
	private int price;
	private int amount;

	// 생성자
	public Wine() {	}

	public Wine(int id, String name, String origin, int price, int amount) {
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.price = price;
		this.amount = amount;
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
}
