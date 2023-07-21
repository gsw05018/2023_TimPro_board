package com.gsw.sbs_tim.java.ssg.dto;


//유사한 데이터를 모아두는 것
public class Article {

	public int id; //번호
	public String title; //제목 
	public String body; //내용
	public int memberId; 
	public String regDate;
	public int hit;
	
	
	public Article(int id, String title, String body, int memberId, String regDate, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
		this.hit = hit;
	}
	
	
	//생성자 > 객체를 만들때 반든시 실행되는 메서드!!
	
}
