package com.gsw.sbs_tim.java.ssg;

import java.util.ArrayList;
import java.util.Scanner;

import com.gsw.sbs_tim.java.ssg.dto.Article;
import com.gsw.sbs_tim.java.ssg.dto.Member;
import com.gsw.sbs_tim.java.ssg.util.util;

public class Main {

	static Scanner scan = new Scanner(System.in);

	static ArrayList<Article> articles = new ArrayList<>();
	static ArrayList<Member> members = new ArrayList<>();

	static int lastArticleId = 4;
	static int lastMemberId = 1;// 데이터 하나 저장할 때마다 1증가
	static boolean isLogin = false;
	static Member loginedMember = null;

	public static void main(String[] args) {

		Article a1 = new Article(1, "제목1", "내용 1", "익명", util.getNowDateStr(), 0);
		Article a2 = new Article(2, "제목1", "내용 1", "익명", util.getNowDateStr(), 0);
		Article a3 = new Article(3, "제목1", "내용 1", "익명", util.getNowDateStr(), 0);

		articles.add(a1);
		articles.add(a2);
		articles.add(a3);

		while (true) {
			
			if (isLogin = false) {

				System.out.println("해당되는 번호를 입력해주세요 : ");

			} else {

				System.out.printf("%s[%s]해당되는 번호를 입력해주세요 : ", loginedMember, loginedMember.nickName);

			}
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 게시물 작성");
			System.out.println("4. 게시물 목록");
			System.out.println("5. 게시물 검색");
			System.out.println("6. 게시물 수정");
			System.out.println("7. 게시물 삭제");
			System.out.println("8. 게시물 상세페이지");
			System.out.println();

			int cmd = Integer.parseInt(scan.nextLine());
			System.out.println();

			if (cmd == 1) { // write

				dojoin();

			} else if (cmd == 2) { // list

				login();

			} else if (cmd == 3) { // search

				write();

			} else if (cmd == 4) { // modify

				list();

			} else if (cmd == 5) { // delete

				search();

			} else if (cmd == 6) {

				modify();

			} else if (cmd == 7) {

				delete();

			} else if (cmd == 8) {

				detail();

			} else {
				System.out.println("다시 입력해주세요");
				System.out.println();
			}
		}

	}

	// 회원가입
	private static void dojoin() {

		System.out.println("< 회원가입을 진행합니다 >");
		System.out.println();
		System.out.printf(" 아이디를 입력해주세요 : ");
		String loginId = scan.nextLine();
		System.out.printf(" 비밀번호를 입력해주세요 : ");
		String loginPw = scan.nextLine();
		System.out.printf(" 닉네임을 입력해주세요 : ");
		String nickName = scan.nextLine();
		System.out.println();
		System.out.println(" == 회원가입이 완료되었습니다 ==");
		System.out.println();

		Member member = new Member(lastMemberId, loginId, loginPw, nickName);
		members.add(member);
		lastMemberId++;

		printMembers(members);
	}

	// 회원가입 된 목록을 보여주는 함수
	private static void printMembers(ArrayList<Member> members) {

		System.out.println("현재 회원 가입 된 회원 목록");
		System.out.println();
		for (int i = 0; i < members.size(); i++) {
			Member member = members.get(i);

			System.out.println("아이디 : " + member.loginId);
			System.out.println("비밀번호 : " + member.loginPw);
			System.out.println("이름 : " + member.nickName);
			System.out.println();
		}
	}

	// 로그인 함수
	private static void login() {
		System.out.println("< 로그인 >");
		System.out.printf("아이디 : ");
		String loginId = scan.nextLine();
		System.out.printf("비밀번호 : ");
		String loginPw = scan.nextLine();

		boolean isExist = false;

		for (int i = 0; i < members.size(); i++) {

			Member member = members.get(i);

			if (loginId.equals(loginId)) {
				if (loginPw.equals(loginPw)) {
					System.out.println(member.nickName + "님 반갑습니다");

					isExist = true;
					isLogin = false;
					loginedMember = member;

					System.out.println();
				}
			}
		}

		if (isExist == false) {
			System.out.println("잘못된 회원정보입니다");

		}
	}

	// 게시물 작성
	private static void write() {

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
	private static void list() {
		printArticles(articles);
	}

	// 게시물 검색
	private static void search() {
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
	private static void modify() {
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
	private static void delete() {

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
	private static void detail() {
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
	private static void printArticle(Article article) {

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
	private static void printArticles(ArrayList<Article> targetList) {

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
	public static int findIndexByArticled(int targetId) {

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
