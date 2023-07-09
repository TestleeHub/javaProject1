package service;

import model.Member;

public interface Login {
	public void join(Member member);
	
	public void hostLogin(Member member) throws Exception;
	
	public void guestLogin(Member member) throws Exception;
	
	public void exit();
}
