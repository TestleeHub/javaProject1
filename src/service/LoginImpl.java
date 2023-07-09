package service;

import model.Member;
import model.MemberHashMap;

public class LoginImpl implements Login {

	private static LoginImpl Instance = new LoginImpl();
	MemberHashMap memberHashMap;

	private LoginImpl() {	
		memberHashMap = new MemberHashMap();
	}

	public static LoginImpl getInstance() {
		if (Instance == null)
			return new LoginImpl();
		else
			return Instance;
	}

	@Override
	public void join(Member member) {
		memberHashMap.addMemberList(member);
	}

	@Override
	public void hostLogin(Member member) throws Exception {
		if(!(member.getId().equals(Host.ID)) || !(member.getPassword().equals(Host.PASSWORD))) 
			throw new Exception("관리자 로그인 정보가 일치하지 않습니다. 다시 입력해주세요.");
	}

	@Override
	public void guestLogin(Member member) throws Exception {
		if(!memberHashMap.containsMember(member))
			throw new Exception("회원 로그인 정보가 일치하지 않습니다. 다시 입력해주세요.");
	}

	@Override
	public void exit() {
		
	}


}
