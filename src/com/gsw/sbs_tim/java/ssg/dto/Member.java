package com.gsw.sbs_tim.java.ssg.dto;

public class Member {

	public int id;
	public String loginId;
	public String loginPw;
	public String nickName;
	
	
	public Member(int id, String loginId, String loginPw, String nickName) {
		super();
		
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.nickName = nickName;
	}
	
}
