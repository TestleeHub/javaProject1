package model;

import java.util.HashMap;
import java.util.Map;

public class ProductHashMap {
	Map<Integer, Wine> productHashMap;
	
	public ProductHashMap() {
		productHashMap = new HashMap<Integer, Wine>();
	}
	
	public void addMemberList(Wine wine) {
		productHashMap.put(wine.getId(), wine);
	}
	
	public void removeMemberList(Integer id) {
		productHashMap.remove(id);
	}
	
	public boolean containsId(int id) {
		return productHashMap.containsKey(id);
	}
	
//	public boolean containsMember(Member member) {
//		for(String id : memberHashMap.keySet()) {
//			if(id.equals(member.getId())) {
//				if(memberHashMap.get(id).getPassword().equals(member.getPassword())) 
//					return true;
//			}
//		}
//		return false;
//	}
}
