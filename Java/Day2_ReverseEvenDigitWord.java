package Java;

public class Day2_ReverseEvenDigitWord {

	static String str = "When the world realise its own mistakes, corona will dissolve automatically";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		String[] array = str.split(" ");
		
		for (int i = 0; i < array.length; i++) {
			
			if (i % 2 != 0) {
				
				for (int j = array[i].length() - 1; j >= 0; j--)
					
					System.out.print(array[i].charAt(j));
			} 
			else 
			{
				System.out.print(array[i]);
			}
			
			System.out.print(" ");
		}
	}

}
