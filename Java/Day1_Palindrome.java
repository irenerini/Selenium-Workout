package Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1_Palindrome {

	static String str = "testleaf"; // change to malayalam or jsjsjsjs or jsjsjsj

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		withStringBuffer();
		withStringReverse();
		withArrayList();
	}

	public static void compareString(String reverse) {

		if (str.equals(reverse)) {

			System.out.println("It's a Palindrome");
		} else {
			System.out.println("It's not a Palindrome");
		}
	}

	public static void withStringBuffer() {

		System.out.println("\n" + "****** String Buffer ******");

		StringBuilder str1 = new StringBuilder(str);

		String reverse = str1.reverse().toString();

		compareString(reverse);

	}

	public static void withStringReverse() {

		System.out.println("\n" + "****** String Reverse ******");

		char[] sta = str.toCharArray();
		String reverse = "";

		for (int i = sta.length - 1; i >= 0; i--) {

			reverse += sta[i];
		}

		// System.out.println(reverse);

		compareString(reverse);
	}

	public static void withArrayList() {

		System.out.println("\n" + "****** Array List ******");

		char[] ch = str.toCharArray();
		List<Character> chr = new ArrayList<>();
		List<Character> che = new ArrayList<>();

		for (Character s : ch) {

			chr.add(s);
			che.add(s);
		}

		Collections.reverse(chr);
		System.out.println(che);
		System.out.println(chr);

		if (chr.equals(che)) {

			System.out.println("It's a Palindrome");
		} else {

			System.out.println("It's not a Palindrome");

		}

	}
}