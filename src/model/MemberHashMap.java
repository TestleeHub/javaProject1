package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberHashMap {
	Map<String, Member> memberHashMap;
	
	public MemberHashMap() {
		memberHashMap = new HashMap<String, Member>();
	}
	
	public void addMemberList(Member member) {
		memberHashMap.put(member.getId(), member);
	}
	
	public void removeMemberList(String id) {
		memberHashMap.remove(id);
	}
	
	public boolean containsMember(Member member) {
		for(String id : memberHashMap.keySet()) {
			if(id.equals(member.getId())) {
				if(memberHashMap.get(id).getPassword().equals(member.getPassword())) 
					return true;
			}
		}
		return false;
	}
}
