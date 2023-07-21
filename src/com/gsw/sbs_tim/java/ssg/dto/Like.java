package com.gsw.sbs_tim.java.ssg.dto;

public class Like {

	int id;
	public int articleId;
	public int memberId;
	String regDate;

	public Like(int id, int articleId, int memberId, String regDate) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.memberId = memberId;
		this.regDate = regDate;
	}

}
