package Java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Day5_DuplicatesInList {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "When life gives you lemons, make lemonade";
		
		Set<Character> set = new HashSet<Character>();
		
		
		System.out.println("Duplicates chars are ");
		for (Character ch : str.toCharArray()) {
			
			if(!set.add(ch)){
				System.out.print(ch);
			}
			
		}
		
		//System.out.println("\n"+set);
		System.out.println("***********");
		
		Set<Character> uniqueChars = new HashSet<Character>();
		Set<Character> repeats = new HashSet<Character>();
		for(int i = 0; i < str.length() - 1; i++) {
		    if (!uniqueChars.add(str.charAt(i))) {
		        repeats.add(str.charAt(i));
		    }
		}
		
	//	System.out.println(uniqueChars);
		System.out.println(repeats);
		
		
		Map <Character, Integer> charMap = new HashMap<Character, Integer> ();
	    for (char c : str.toCharArray()) {
	        Integer count = charMap.get(new Character(c));
	        if (count == null) {
	            charMap.put(new Character(c), 1);
	        }
	        else {
	            charMap.put(new Character(c), count.intValue()+1);
	        }
	    }

	    
	  //  System.out.println("map "+charMap);
	    
	    for (Entry<Character, Integer> obj : charMap.entrySet()) {

	        if (obj.getValue() > 1) {
	            System.out.println(obj.getKey());
	        }
	    }
	}

}
