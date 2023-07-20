package com.gsw.sbs_tim.java.ssg.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.gsw.sbs_tim.java.ssg.dto.Article;
import com.gsw.sbs_tim.java.ssg.util.util;

public class ArticleController {

	ArrayList<Article> articles = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	int lastArticleId = 4;

	public void doCommand(int cmd) {

		if (cmd == 4) { 

			write();

		} else if (cmd == 5) { 

			list();

		} else if (cmd == 6) { 

			search();

		} else if (cmd == 7) {

			modify();

		} else if (cmd == 8) {

			delete();

		} else if (cmd == 9) {

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

		Article wirteArticle = new Article(lastArticleId, title, body, "익명", util.getNowDateStr(), 0);
		articles.add(wirteArticle);
		lastArticleId++;
	}

	// 게시물 목록
	private void list() {
		printArticles(articles);
	}

	// 게시물 검색
	private void search() {
		System.out.printf("검색 키워드를 입력해주세요 : ");
		System.out.println();
		String keyWord = scan.nextLine();

		ArrayList<Article> searchedList = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);

			if (article.title.contains(keyWord)) {
				searchedList.add(article);
			} else if (article.body.contains(keyWord)) {
				searchedList.add(article);
			} else if (article.nickName.contains(keyWord)) {
				searchedList.add(article);
			}
		}

		printArticles(searchedList);
	}

	// 게시물 수정
	private void modify() {
		System.out.print("수정할 게시물 번호 : ");
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

			articles.set(index, new Article(lastArticleId, title, body, "익명", util.getNowDateStr(), 0));

			list();

			System.out.println("게시물 수정이 완료되었습니다.");
			System.out.println();

		}

	}

	// 게시물 삭제
	private void delete() {

		System.out.print("삭제할 게시물 번호 : ");
		System.out.println();
		int targetId = Integer.parseInt(scan.nextLine());

		int index = findIndexByArticled(targetId);

		if (index == -1) {
			System.out.println("없는 게시물 번호입니다.");
			System.out.println();
			System.out.println("다시 입력해주세요");

		} else {// 삭제코드

			articles.remove(index);

			System.out.println("삭제가 완료되었습니다");
			System.out.println();
			list();

		}

	}

	// 게시물 상세페이지
	private void detail() {
		System.out.println("상세보기 할 게시물 번호를 입력해주세요");
		int id = Integer.parseInt(scan.nextLine());

		int targetIndex = findIndexByArticled(id);

		if (targetIndex == -1) {
			System.out.println("없는 게시물입니다");
		} else {
			Article article = articles.get(targetIndex);
			article.hit++; // 조회수 1증가
			printArticle(article);
		}
	}

	// 게시물 목록 하나만 출력하는 함수
	private void printArticle(Article article) {

		System.out.printf("<%d번 게시물>\n", article.id);
		System.out.println("번호 : " + article.id);
		System.out.println("제목 : " + article.title);
		System.out.println("내용 : " + article.body);
		System.out.println("작성자 : " + "익명");
		System.out.println("등록날짜 : " + util.getNowDateStr());
		System.out.println("조회수 : " + article.hit);
		System.out.println("");

	}

	// 게시물 목록 여려개 출력하는 함수
	private void printArticles(ArrayList<Article> targetList) {

		for (int i = 0; i < targetList.size(); i++) {
			Article article = targetList.get(i);

			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("내용 : " + article.body);
			System.out.println("작성자 : " + "익명");
			System.out.println("등록날짜 : " + util.getNowDateStr());
			System.out.println("조회수 : " + article.hit);
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
