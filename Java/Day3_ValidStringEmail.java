package Java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3_ValidStringEmail {

	static String str = "balaji.chandrasekaran@testleaf.com";
	static String str1 = "balaji.c@tunatap.co.uk";
	static String str2 = "naveen e@tl.com";
	static String usr1 = "Balaji_1";
	static String usr2 = "Testleaf$123";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		withPatternRegex(str);
		withPatternRegex(str1);
		withPatternRegex(str2);
		validEmail(usr1);
		validEmail(usr2);

	}

	public static void withPatternRegex(String stra) {

		String regex = "[a-zA-Z0-9._]+@[a-z0-9]+.[a-z.]{2,}";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(stra);
		System.out.println(mat.matches());

	}

	public static void validEmail(String stre) {

		String regex1 = "[a-zA-Z0-9._$@]{8,}";
		Pattern patt = Pattern.compile(regex1);
		Matcher matt = patt.matcher(stre);
		System.out.println(matt.matches());

	}

}
