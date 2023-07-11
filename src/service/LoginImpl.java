package service;

import java.util.Scanner;

import model.Member;
import model.MemberMap;
import view.LoginView;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 로그인 관련 서비스 클래스
 * 				1. 회원가입
 * 				2. 관리자 로그인
 * 				3. 회원 로그인
 */

public class LoginImpl implements Login {
	private String curLoginId;
	private Scanner input;
	private LoginView loginView;

	private static LoginImpl Instance = new LoginImpl();
	private MemberMap memberHashMap;

	private LoginImpl() {	
		memberHashMap = new MemberMap();
	}

	public static LoginImpl getInstance() {
		if (Instance == null)
			return new LoginImpl();
		else
			return Instance;
	}
	
	public void setInput(Scanner input) {
		this.input = input;
	}
	
	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}
	
	@Override
	public void hostLogin() {
		while (true) {
			System.out.print("관리자 ID : ");
			String id = input.next();
			System.out.print("관리자 PW : ");
			String password = input.next();
			try {
				hostLogin(new Member(id, password));
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}

	@Override
	public void guestLogin() {
		while (true) {
			System.out.print("회원 ID : ");
			String id = input.next();
			System.out.print("회원 PW : ");
			String password = input.next();
			try {
				guestLogin(new Member(id, password));
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}

	@Override
	public void join() {
		loginView.joinView();
		while (true) {
			System.out.print("ID : ");
			String id = input.next();
			System.out.print("PW : ");
			String password = input.next();
			try {
				memberHashMap.addMemberList(new Member(id, password));
				loginView.joinSuccessView();
				return;
			} catch (Exception e) {
				loginView.joinFailView();
				System.out.println(e.getMessage());
				continue;
			}
		}
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
		else {
			GuestImpl.getInstance().login(memberHashMap.getMemberMap().get(member.getId()));
			setCurLoginId(member.getId());
		}
	}
	
	public void writeFileMemberData() {
		memberHashMap.writeFileMemberData();
	}
	
	public MemberMap getMemberHashMap() {
		return memberHashMap;
	}
	
	public String getCurLoginId() {
		return curLoginId;
	}
	
	public void setCurLoginId(String curLoginId) {
		this.curLoginId = curLoginId;
	}
}
