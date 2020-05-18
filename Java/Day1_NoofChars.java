package Java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1_NoofChars {

	String str = "You have no choice other than following me!";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Day1_NoofChars obj = new Day1_NoofChars();
		obj.withCharArray();
		obj.withJava8filter();
		obj.withPatternClass();
		obj.withReplaceAll();
		obj.withForEach();

	}

	public void withCharArray() {

		System.out.println("****** For loop method ******");

		char[] starr = str.toCharArray();

		int j = 0;

		for (int i = 0; i < starr.length; i++) {

			if (starr[i] == starr[1]) {

				j = j + 1;

			}

		}

		System.out.println("Occurance of char 'o' is " + j+"\n");
	}

	public void withJava8filter() {

		System.out.println("****** Java 8 filter method ******");

	
		long java8 = str.chars().filter(ch -> ch == 'o').count();
	
		System.out.println("Number of 'o' characters: " + java8+"\n");
	}

	public void withPatternClass() {

		System.out.println("****** Pattern Class ******");

		String regex = "[o]";
		// Compiling the regular expression
		Pattern pattern = Pattern.compile(regex);
		// Retrieving the matcher object
		Matcher matcher = pattern.matcher(str);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		System.out.println("Number of 'o' characters: " + count+"\n");

	}
	
	public void withReplaceAll() {
		
		System.out.println("****** Replace All ******");

		int chs = str.replaceAll("[^o]", "").length();
		System.out.println("Number of 'o' characters: " + chs+"\n");

		
	}

	public void withForEach() {
		
		System.out.println("****** For Each ******");

		int count = 0;
	    for (char c : str.toCharArray()) {
	        if (c == 'o') {
	           ++count;
	        }
	    }
	    
		System.out.println("Number of 'o' characters: " + count+"\n");

		
	}
	
}
