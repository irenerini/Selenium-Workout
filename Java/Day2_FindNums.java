package Java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2_FindNums {

	static String str = "asdf1qwer9as8d7";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		withPatternRegex();
		withRegex();
		withCharDigit();
	}

	public static void withPatternRegex() {

		System.out.println("****** Pattern Regex ******");

		String regex = "[\\p{Digit}]";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(str);
		int a = 0;
		int b = 0;

		while (mat.find()) {
			a = Integer.parseInt(mat.group());
			b = b + a;
		}

		System.out.println("Sum of the given numbers in String is " + b);
	}

	public static void withRegex() {

		System.out.println("\n" + "****** Regex ******");

		String sta = str.replaceAll("[\\D]", "");
		char[] ch = sta.toCharArray();
		int s = 0;

		for (char c : ch) {

			s += Integer.parseInt(String.valueOf(c));

		}
		System.out.println("Sum of the given numbers in String is " + s);

	}

	public static void withCharDigit() {

		System.out.println("\n" + "****** Character is Digit ******");

		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				sum += Integer.parseInt(String.valueOf(str.charAt(i)));

			}

		}
		System.out.println("Sum of the given numbers in String is " + sum);

	}

}
