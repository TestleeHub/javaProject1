package model;

public class Member {
	private String id;
	private String password;
	
	public Member() {	}
	public Member(String id, String password) throws Exception {
		if(id.length() > 20 || id.length() < 4) throw new Exception("아이디는 4~20자 입니다. 다시 입력하세요");
		if(password.length() < 4) throw new Exception("암호는 4자 이상이어야 합니다. 다시 입력하세요");
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
