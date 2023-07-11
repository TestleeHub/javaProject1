package service;

import model.Member;

public interface Guest {
	public void login(Member loginMember);

	void deleteCart();

	void addCart();

	void cartProductBuy();

	void NowBuy();

	void Refund();
}
