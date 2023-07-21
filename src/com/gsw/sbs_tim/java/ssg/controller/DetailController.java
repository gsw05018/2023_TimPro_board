package com.gsw.sbs_tim.java.ssg.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.gsw.sbs_tim.java.ssg.dto.Article;
import com.gsw.sbs_tim.java.ssg.dto.Like;
import com.gsw.sbs_tim.java.ssg.dto.Reply;
import com.gsw.sbs_tim.java.ssg.util.util;

public class DetailController {

	Scanner scan = new Scanner(System.in);

	ArrayList<Reply> replys = new ArrayList<>();
	ArrayList<Like> likes = new ArrayList<>();
	int lastReplyId = 1;
	int lastLikeId = 1;

	public void doCommand(int cmd, Article article) {
		if (cmd == 1) {

			reply(article);

		} else if (cmd == 2) {

			deleteReply(article);

		} else if (cmd == 3) {

			modifyReply();

		} else if (cmd == 4) {

			like(article);

		}
	}

	// 댓글
	private void reply(Article article) {

		System.out.println("댓글 내용을 입력해주세요");
		System.out.printf("내용 : ");
	
		String body = scan.nextLine();
		int memberId = MemberController.loginedMember.id;
		String regDate = util.getNowDateStr();

		Reply reply = new Reply(lastReplyId, article.id, body, memberId, regDate);
		replys.add(reply);
		lastReplyId++;
		System.out.println("댓글이 등록되었습니다");
		System.out.println();

	}

	// 댓글 삭제
	private void deleteReply(Article article) {
		System.out.printf("삭제할 댓글번호 입력해주세요 : ");
		int id = Integer.parseInt(scan.nextLine());

		for (int i = 0; i < replys.size(); i++) {
			Reply reply = replys.get(i);

			if (replys.size() > 0) {
				replys.remove(replys.get(i));

			}

		} if(replys.size() < 0) {
			System.out.println("삭제할 댓글이 없습니다");
		}

	}

	// 댓글 수정
	private void modifyReply() {

		System.out.printf("수정할 댓글 번호 : ");
		System.out.println();
		int id = Integer.parseInt(scan.nextLine());
		
		int index = findIndexByReply(id);

		if (index == -1) {
			System.out.println("없는 게시물 번호입니다.");
			System.out.println();
		} else {
			System.out.print("제목 : ");
			String title = scan.nextLine();
			System.out.print("내용 : ");
			String body = scan.nextLine();
			System.out.println();

			replys.set(index, new Article(targetId, title, body, 1, util.getNowDateStr(), 0));

			list();

			System.out.println("게시물 수정이 완료되었습니다.");
			System.out.println();

		}
		

		
	}

	// 좋아요
	public void like(Article article) {

		int articleId = article.id;
		int memberId = MemberController.loginedMember.id;
		String regDate = util.getNowDateStr();

		Like searchedLike = getLikeByArticleIdAndMemberId(articleId, memberId);

		if (searchedLike == null) {
			Like like = new Like(lastLikeId, articleId, memberId, regDate);
			likes.add(like);
			System.out.println("해당 게시물을 추천합니다");
		} else {
			likes.remove(searchedLike);
			System.out.println("해당 게시물을 추천을 취소합니다");
		}

	}

	// searchedLike 함수
	public Like getLikeByArticleIdAndMemberId(int articleId, int memberId) {

		for (int i = 0; i < likes.size(); i++) {
			if (likes.get(i).articleId == articleId && likes.get(i).memberId == memberId) {
				return likes.get(i);
			}
		}
		return null;
	}

	// 좋아요 숫자 카운트
	public int getLikeCountOfArticle(int articleId) {

		int count = 0;

		for (int i = 0; i < likes.size(); i++) {
			if (likes.get(i).articleId == articleId) {
				count++;
			}
		}
		return count;
	}

	
	// 댓글 찾는 함수
	public int findIndexByReply(int articleId) {

		int index = -1;

		for (int i = 0; i < replys.size(); i++) {
			Reply reply = replys.get(i);
			if (articleId == reply.id) {
				index = i;
				break;
			}
		}
		return index;
	}
	
}
