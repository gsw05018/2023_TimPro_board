package com.gsw.sbs_tim.java.ssg;

import java.util.Scanner;

import com.gsw.sbs_tim.java.ssg.controller.ArticleController;
import com.gsw.sbs_tim.java.ssg.controller.MemberController;

public class Main {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		ArticleController articleController = new ArticleController();
		MemberController memberController = new MemberController();

		while (true) {
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 로그아웃");
			System.out.println("4. 게시물 작성");
			System.out.println("5. 게시물 목록");
			System.out.println("6. 게시물 검색");
			System.out.println("7. 게시물 수정");
			System.out.println("8. 게시물 삭제");
			System.out.println("9. 게시물 상세페이지");
			System.out.println("0. 게시물 닫기");
			System.out.println();

			if (memberController.loginedMember == null) {

				System.out.printf("이동할 메뉴번호를 입력해주세요 : ");

			} else {

				System.out.printf("%s[%s]이동할 메뉴번호를 입력해주세요 : ", memberController.loginedMember.loginId,
						memberController.loginedMember.nickName);

			}

			int cmd = Integer.parseInt(scan.nextLine());
			System.out.println();
			if(cmd >= 4 && cmd <= 9) {
				articleController.doCommand(cmd);
			}else if(cmd <= 3) {
				memberController.doCommand(cmd);
			}else {
				System.out.println("다시 입력해주시기 바랍니다");
			}
			
			

		}
	}
}
