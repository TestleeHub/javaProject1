package service;

import model.Member;

public interface Login {

	public void join();

	public void guestLogin();

	public void hostLogin();
	
	public void hostLogin(Member member) throws Exception;
	
	public void guestLogin(Member member) throws Exception;
}
