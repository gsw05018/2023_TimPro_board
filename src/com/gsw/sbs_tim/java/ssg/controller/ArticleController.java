package com.gsw.sbs_tim.java.ssg.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.gsw.sbs_tim.java.ssg.dto.Article;
import com.gsw.sbs_tim.java.ssg.dto.Like;
import com.gsw.sbs_tim.java.ssg.dto.Reply;
import com.gsw.sbs_tim.java.ssg.util.util;

public class ArticleController {

	ArrayList<Article> articles = new ArrayList<>();

	MemberController memberController = new MemberController();
	DetailController detailController = new DetailController();
	Scanner scan = new Scanner(System.in);
	int lastArticleId = 1;

	public void doCommand(int cmd) {

		if (cmd == 4) {
			if (memberController.loginedMember == null) {
				System.out.println("로그인이 필요한 기능입니다");
				System.out.println();
				return;
			}
			write();

		} else if (cmd == 5) {

			list();

		} else if (cmd == 6) {

			search();

		} else if (cmd == 7) {
			if (memberController.loginedMember == null) {
				System.out.println("로그인이 필요한 기능입니다");
				System.out.println();
				return;

			}

			modify();

		} else if (cmd == 8) {
			if (memberController.loginedMember == null) {
				System.out.println("로그인이 필요한 기능입니다");
				System.out.println();
				return;
			}
			delete();

		} else if (cmd == 9) {
			if (memberController.loginedMember == null) {
				System.out.println("로그인이 필요한 기능입니다");
				System.out.println();
				return;
			}
			detail();

		} else {
			System.out.println("다시 입력해주세요");
			System.out.println();

		}

	}

	// 게시물 작성
	private void write() {

		System.out.printf("제목을 입력해주세요 : ");
		String title = (scan.nextLine());
		System.out.printf("내용을 입력해주세요 : ");
		String body = (scan.nextLine());
		System.out.println();

		System.out.println("게시물이 저장되었습니다.");
		System.out.println();

		Article wirteArticle = new Article(lastArticleId, title, body, memberController.loginedMember.id,
				util.getNowDateStr(), 0);
		articles.add(wirteArticle);
		lastArticleId++;
	}

	// 게시물 목록
	private void list() {

		ArrayList<Article> article = articles;

		if (articles.size() < 0) {
			System.out.println("게시물이 없습니다");
		}
		printArticles(articles);
	}

	// 게시물 검색
	private void search() {
		System.out.printf("검색 키워드를 입력해주세요 : ");

		String keyWord = scan.nextLine();

		ArrayList<Article> searchedList = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);

			if (article.title.contains(keyWord)) {
				searchedList.add(article);
			} else if (article.body.contains(keyWord)) {
				searchedList.add(article);

			}
		}
		printArticles(searchedList);
	}

	// 게시물 수정
	private void modify() {
		System.out.printf("수정할 게시물 번호 : ");
		System.out.println();
		int targetId = Integer.parseInt(scan.nextLine());

		int index = findIndexByArticled(targetId);

		if (index == -1) {
			System.out.println("없는 게시물 번호입니다.");
			System.out.println();
		} else {
			System.out.print("제목 : ");
			String title = scan.nextLine();
			System.out.print("내용 : ");
			String body = scan.nextLine();
			System.out.println();

			articles.set(index, new Article(targetId, title, body, 1, util.getNowDateStr(), 0));

			list();

			System.out.println("게시물 수정이 완료되었습니다.");
			System.out.println();

		}

	}

	// 게시물 삭제
	private void delete() {

		System.out.printf("삭제할 게시물 번호 : ");
		System.out.println();
		int targetId = Integer.parseInt(scan.nextLine());

		int index = findIndexByArticled(targetId);

		if (index == -1) {
			System.out.println("없는 게시물 번호입니다.");
			System.out.println();
			System.out.printf("다시 입력해주세요");

		} else {// 삭제코드

			articles.remove(index);

			System.out.println("삭제가 완료되었습니다");
			System.out.println();
			list();

		}

	}

	// 게시물 상세페이지
	private void detail() {
		System.out.printf("상세보기 할 게시물 번호를 입력해주세요 : ");
		int id = Integer.parseInt(scan.nextLine());

		int targetIndex = findIndexByArticled(id);

		if (targetIndex == -1) {
			System.out.println("없는 게시물입니다");
		} else {
			Article article = articles.get(targetIndex);
			article.hit++; // 조회수 1증가
			printArticle(article);

			// 상세보기
			while (true) {

				System.out.println("1. 댓글 등록");
				System.out.println("2. 댓글 삭제");
				System.out.println("3. 댓글 수정");
				System.out.println("4. 추천 ");
				System.out.println("5. 메뉴 돌아가기");
				System.out.println();
				System.out.printf("메뉴를 선택해주세요 : ");
				System.out.println();
				int cmd = Integer.parseInt(scan.nextLine());
				if (cmd == 5) {
					break;
				}
				detailController.doCommand(cmd, article);
				printArticle(article);
			}

		}
	}

	// 게시물 목록 하나만 출력하는 함수
	private void printArticle(Article article) {

		String nicknameOfArticle = memberController.getNicknameByMemberId(article.memberId);

		ArrayList<Reply> replys = detailController.replys;

		System.out.println();
		System.out.printf("<%d번 게시물>\n", article.id);
		System.out.println("번호 : " + article.id);
		System.out.println("제목 : " + article.title);
		System.out.println("내용 : " + article.body);
		System.out.println("작성자 : " + nicknameOfArticle);
		System.out.println("등록날짜 : " + util.getNowDateStr());
		System.out.println("댓글수 : " + replys.size());

		Like like = detailController.getLikeByArticleIdAndMemberId(article.id, MemberController.loginedMember.id);

		int count = detailController.getLikeCountOfArticle(article.id);

		if (like == null) {
			System.out.println("추천 : ♡ " + count);

		} else {
			System.out.println("추천 : ♥ " + count);

		}
		System.out.println("조회수 : " + article.hit);
		System.out.println("");
		System.out.println("< 댓글 > ");

		for (int i = 0; i < replys.size(); i++) {

			Reply reply = replys.get(i);

			if (reply.articleId == article.id) {
				String nicknameOfReply = memberController.getNicknameByMemberId(reply.memberId);

				System.out.println("번호 : " + reply.id);
				System.out.println("내용 : " + reply.body);
				System.out.println("작성자 : " + nicknameOfReply);
				System.out.println("작성일 : " + reply.regDate);
				System.out.println();

			}

		}

	}

	// 게시물 목록 여려개 출력하는 함수
	private void printArticles(ArrayList<Article> targetList) {

		for (int i = 0; i < targetList.size(); i++) {
			Article article = targetList.get(i);

			String nickname = memberController.getNicknameByMemberId(article.memberId);
			ArrayList<Reply> replys = detailController.replys;

			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("내용 : " + article.body);
			System.out.println("작성자 : " + nickname);
			System.out.println("등록날짜 : " + util.getNowDateStr());
			System.out.println("댓글수 : " + replys.size());
			System.out.println("조회수 : " + article.hit);
			Like like = detailController.getLikeByArticleIdAndMemberId(article.id, MemberController.loginedMember.id);

			int count = detailController.getLikeCountOfArticle(article.id);

			if (like == null) {
				System.out.println("추천 : ♡ " + count);

			} else {
				System.out.println("추천 : ♥ " + count);

			}

			System.out.println();
		}

	}

	// 게시물 번호 찾기 함수
	public int findIndexByArticled(int targetId) {

		int index = -1;

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (targetId == article.id) {
				index = i;
				break;
			}
		}
		return index;
	}


}
