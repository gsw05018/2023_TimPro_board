package com.gsw.sbs_tim.java.ssg;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner scan = new Scanner(System.in);

	static ArrayList<Integer> ids = new ArrayList<>();
	static ArrayList<String> titles = new ArrayList<>();
	static ArrayList<String> bodies = new ArrayList<>();

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

		ids.add(lastIndex + 1);
		System.out.printf("제목을 입력해주세요 : ");
		titles.add(scan.nextLine());
		System.out.printf("내용을 입력해주세요 : ");
		bodies.add(scan.nextLine());
		System.out.println();

		System.out.println("게시물이 저장되었습니다.");
		System.out.println();
		lastIndex++;

	}

	// 게시물 목록
	private static void list() {
		for (int i = 0; i < lastIndex; i++) {
			System.out.println("번호 : " + ids.get(i));
			System.out.println("제목 : " + titles.get(i));
			System.out.println("내용 : " + bodies.get(i));
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

			titles.set(index, title);
			bodies.set(index, body);

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

			ids.remove(index);
			titles.remove(index);
			bodies.remove(index);

			System.out.println("삭제가 완료되었습니다");
			System.out.println();
			list();

		}

	}

	// 게시물 번호 찾기 함수
	public static int findIndexByArticled(int targetId) {

		int index = -1;

		for (int i = 0; i < lastIndex; i++) {
			if (targetId == ids.get(i)) {
				index = i;
				break;
			}
		}
		return index;
	}

}
