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

			modifyReply(article);

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
		System.out.println();
		int targetId = Integer.parseInt(scan.nextLine());

		int index = findIndexByReply(targetId);

		if (index == -1) {
			System.out.println("댓글이 없습니다.");
			System.out.println();
			System.out.printf("다시 입력해주세요");

		} else {// 삭제코드

			replys.remove(index);

			System.out.println("삭제가 완료되었습니다");
			System.out.println();

		}

	}

	// 댓글 수정
	private void modifyReply(Article article) {

		System.out.printf("수정할 댓글 번호 : ");
		System.out.println();
		int targetId = Integer.parseInt(scan.nextLine());

		int index = findIndexByReply(targetId);

		if (index == -1) {
			System.out.println("없는 댓글 번호입니다.");
			System.out.println();
		} else {

			int memberId = MemberController.loginedMember.id;
			String regDate = util.getNowDateStr();
			System.out.print("내용 : ");
			String body = scan.nextLine();
			System.out.println();

			replys.set(index, new Reply(targetId, article.id, body, memberId, regDate));

			System.out.println("댓글 수정이 완료되었습니다.");
			System.out.println();

		}

	}

	// 게시물 좋아요
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

	// 게시물 좋아요 숫자 카운트
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
	public int findIndexByReply(int targetId) {

		int index = -1;

		for (int i = 0; i < replys.size(); i++) {
			Reply reply = replys.get(i);
			if (targetId == reply.id) {
				index = i;
				break;
			}
		}
		return index;
	}

}
