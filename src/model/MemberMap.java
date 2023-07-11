package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/*
 * 작성자 	: 이연우
 * 작성일자 	: 2023/07/07
 * 클래스 설명	: 회원가입한 고객의 HashMap 관리
 * 				1. 고객의 목록을 추가, 조회 기능 제공 
 */

public class MemberMap {
	private Map<String, Member> memberMap;
	
	public MemberMap() {
		memberMap = new HashMap<String, Member>();
		try {
			readMemberData();
		} catch (Exception e) {
			e.getMessage(); // 있으면 읽는것이기 때문에 파일 없음 에외시 아무것도 안함
		}
	}
	
	public void addMemberList(Member member) {
		memberMap.put(member.getId(), member);
		
		writeFileMemberData();
	}
	
	public void removeMemberList(String id) {
		memberMap.remove(id);
		writeFileMemberData();
	}
	
	public boolean containsMember(Member member) {
		for(String id : memberMap.keySet()) {
			if(id.equals(member.getId())) {
				if(memberMap.get(id).getPassword().equals(member.getPassword())) 
					return true;
			}
		}
		return false;
	}
	
	public Map<String, Member> getMemberMap() {
		return memberMap;
	}
	
	public void writeFileMemberData(){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("MemberMap.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(memberMap);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readMemberData() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("MemberMap.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		memberMap = (Map<String, Member>)ois.readObject();
		if (fis != null)
			fis.close();
		if (ois != null)
			ois.close();
	}
}
