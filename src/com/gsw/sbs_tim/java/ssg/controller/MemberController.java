package com.gsw.sbs_tim.java.ssg.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.gsw.sbs_tim.java.ssg.dto.Member;

public class MemberController {

	static Scanner scan = new Scanner(System.in);
	ArrayList<Member> members = new ArrayList<>();
	int lastMemberId = 1;

	public static Member loginedMember = null;

	public MemberController() {
		for (int i = 0; i < members.size(); i++) {
			loginedMember = members.get(i);
		}
	}

	public void doCommand(int cmd) {

		if (cmd == 1) { // write

			dojoin();

		} else if (cmd == 2) { // list

			login();

		} else if (cmd == 3) {
			logout();
		}
	}

	// 회원가입
	private void dojoin() {

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
	private void printMembers(ArrayList<Member> members) {

		System.out.println("< 현재 회원 가입 된 회원 목록 >");
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
	private void login() {
		System.out.println("< 로그인 >");
		System.out.printf("아이디 : ");
		String loginId = scan.nextLine();
		System.out.printf("비밀번호 : ");
		String loginPw = scan.nextLine();

		for (int i = 0; i < members.size(); i++) {

			Member member = members.get(i);

			if (member.loginId.equals(loginId)) {
				if (member.loginPw.equals(loginPw)) {
					System.out.println(member.nickName + "님 반갑습니다");

					loginedMember = member;

					System.out.println();
				}
			}
		}

		if (loginedMember == null) {
			System.out.println("잘못된 회원정보입니다");
			System.out.println();

		}
	}

	// 로그아웃
	private void logout() {
		
		
		loginedMember = null;
		
		System.out.println("로그아웃 되었습니다.");
		
	}
	

	// memberId 찾는함수
	public String getNicknameByMemberId(int memberId) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).id == memberId) {
				return members.get(i).nickName;
			}
		}

		return null;
	}

	private void makeTestData() {
		Member m1 = new Member(1, "gsw05018", "1234", "홍길동");
		Member m2 = new Member(2, "gsw050181", "1234", "이순신");

		members.add(m1);
		members.add(m2);
	}
}
