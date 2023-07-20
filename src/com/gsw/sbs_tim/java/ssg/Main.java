package com.gsw.sbs_tim.java.ssg;

import java.util.Scanner;

public class Main {

	static Scanner scan = new Scanner(System.in);
	static int[] ids = new int[3]; // 번호 저장소
	static String[] titles = new String[3]; // 제목 저장소
	static String[] bodies = new String[3]; // 내용 저장소
	static int lastIndex = 0; // 데이터 하나 저장할 때마다 1증가
	// int articleId = 1; // 게시물 번호

	public static void main(String[] args) {

		while (true) {
			System.out.println("1. 게시물 작성");
			System.out.println("2. 게시물 목록");
			System.out.println("3. 게시물 수정");
			System.out.println("4. 게시물 삭제");
			System.out.println("5. 게시물 작성");
			System.out.println();

			System.out.printf("해당되는 번호를 입력해주세요 : ");
			int cmd = Integer.parseInt(scan.nextLine());
			System.out.println();

			if (cmd == 1) { // write

				write();

			} else if (cmd == 2) { // list

				list();

			} else if (cmd == 3) { // modify

				modify();

			} else if (cmd == 4) { // delete
				delete();
			}

			else {
				System.out.println("다시 입력해주세요");
				System.out.println();
			}
		}

	}

	// 게시물 작성
	private static void write() {

		ids[lastIndex] = lastIndex + 1;
		System.out.printf("제목을 입력해주세요 : ");
		titles[lastIndex] = scan.nextLine();
		System.out.printf("내용을 입력해주세요 : ");
		bodies[lastIndex] = scan.nextLine();
		System.out.println();

		System.out.println("게시물이 저장되었습니다.");
		System.out.println();
		lastIndex++;

	}

	// 게시물 목록
	private static void list() {
		for (int i = 0; i < lastIndex; i++) {
			System.out.println("번호 : " + ids[i]);
			System.out.println("제목 : " + titles[i]);
			System.out.println("내용 : " + bodies[i]);
			System.out.println();
		}

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

			titles[index] = title;
			bodies[index] = body;

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

			for (int i = index; i < lastIndex; i++) {
				ids[i] = ids[i + 1];
			}

			for (int i = index; i < lastIndex; i++) {
				titles[i] = titles[i + 1];
			}

			for (int i = index; i < lastIndex; i++) {
				bodies[i] = bodies[i + 1];
			}
			lastIndex--;

			
			System.out.println("삭제가 완료되었습니다");
			System.out.println();
			list();
			
		}

	}

	// 게시물 번호 찾기 함수
	public static int findIndexByArticled(int targetId) {

		int index = -1;

		for (int i = 0; i < lastIndex; i++) {
			if (targetId == ids[i]) {
				index = i;
				break;
			}
		}
		return index;
	}

}
