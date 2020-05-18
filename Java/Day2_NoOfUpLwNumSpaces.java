package Java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2_NoOfUpLwNumSpaces {

	static String str = "1. It is Work from Home not Work for Home";

	public static void main(String[] args) {
		
		patternReusable("[\\p{Upper}]", "Upper");
		patternReusable("[\\p{Lower}]", "Lower");
		patternReusable("[\\p{Digit}]", "Numbers");
		patternReusable("[\\s]", "Spaces");
		characterClassReusable();
		withRegex();

	}

	public static void patternReusable(String regex, String whichchars) {

		System.out.println("****** Pattern Class ******");

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		System.out.println("Number of " + whichchars + " characters: " + count + "\n");

	}

	public static void characterClassReusable() {

		System.out.println("****** Character Class ******");

		int upper = 0, lower = 0, nums = 0, spaces = 0;

		for (int i = 0; i < str.length(); i++) {

			if (Character.isUpperCase(str.charAt(i))) {

				upper++;
			} else if (Character.isLowerCase(str.charAt(i))) {

				lower++;
			} else if (Character.isDigit(str.charAt(i))) {
				nums++;
			} else if (Character.isSpaceChar(str.charAt(i))) {
				spaces++;
			}

		}

		System.out.println("Number of Upper characters: " + upper);
		System.out.println("Number of Lower characters: " + lower);
		System.out.println("Number of Digit characters: " + nums);
		System.out.println("Number of Spaces characters: " + spaces);

	}

	public static void withRegex() {

		System.out.println("\n"+"****** Replace All ******");

		System.out.println("Number of Upper characters: " + str.replaceAll("[^A-Z]", "").length());
		System.out.println("Number of Lower characters: " + str.replaceAll("[^a-z]", "").length());
		System.out.println("Number of Digit characters: " + str.replaceAll("[^0-9]", "").length());
		System.out.println("Number of Spaces characters: " + str.replaceAll("[^\\s]", "").length());
		
	}

}
