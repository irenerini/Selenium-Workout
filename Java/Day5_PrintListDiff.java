package Java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5_PrintListDiff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "Bugatti Chiron";
		
		List<Character> ls = new ArrayList<Character>();

		for (Character ch : str.toCharArray()) {
			ls.add(ch);
			
		}
		
		System.out.println("Simple Print");
		System.out.println(ls);
		
		
		System.out.println("For Each Print");
		for (Character charc : ls) {
			System.out.print(charc);
		}
		
		
		System.out.println("\n"+"For Each java 8 Print");
		ls.forEach(System.out::print);
		
		
		System.out.println("\n"+"While Print");
		Iterator<Character> it = ls.iterator();
		
		while(it.hasNext()) {
			System.out.print(it.next());
		}
		
		
	}

}
