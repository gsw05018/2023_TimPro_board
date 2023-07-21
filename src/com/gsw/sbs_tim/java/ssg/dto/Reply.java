package com.gsw.sbs_tim.java.ssg.dto;

public class Reply {

	public int id; // 댓글 식별자 필요(수정, 삭제하기위해서)
	public int articleId; // 댓글이 달린 게시물의 번호
	public String body;
	public int memberId; // loginId로 해도됨 > 중복체크를 안넣었기 때문에 id로 작성자로 설정함
	public String regDate;

	public Reply(int id, int articleId, String body, int memberId, String regDate) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
	}

	public Reply() {
		// TODO Auto-generated constructor stub
	}

	

}
