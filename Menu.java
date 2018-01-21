/*=================================================================
Assignment 1: Menu
Lorraine Li
Mar.23,2016
Java, Version 1.7
=================================================================
Problem Definition 	– Allows the user to choose which program to run: the blob crusher game or the permutations program.
Input – an integer (1,2, or 3) that indicates the user's choice
Output – the program chosen
Process – based on which number the user chose, the program accesses a specific class
=================================================================
 */ 

import java.io.*;
public class Menu {
	/**main method:
	 * This procedural method is called automatically and is used to organize the calling of other methods defined in the class. It accessess the method
	 * the user requests. 
	 * 
	 * menu - an object used to get access to non-static methods defined in the class <type Menu>
	 * b - an object used to get access to the non-static methods <type BlobCrusher>
	 * choice - variable to store the user's input which is the program they chose <type String>
	 * perm1 - an object used to get access to non-static methods <type Perm1>
	 *
	 * @param args <type String>
	 * @throws IOException	
	 * @return void
	 */
	public static void main(String[] args) throws IOException{
		Menu menu = new Menu();
		Perm1 perm1 = new Perm1();
		BlobCrusher b = new BlobCrusher();
		String choice;
		while (true) {
			menu.choice();
			choice = menu.input();
			if (choice.equals("1")){

				perm1.main(args);
			}
			else if (choice.equals("2")){
				b.main(args);
			}
			else if (choice.equals("3"))
				break;
			else
				System.out.println("That is not a valid option.");
		}
		System.out.println("Thank you for using my programs. I hope you enjoyed them!");
	}// end of main method

	/**input method:
	 * This functional method accepts user input and returns it to the main method
	 * 
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 *
	 * @param none
	 * @throws IOException	
	 * @return the user input <type String>
	 */
	public String input()throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}// end of input method

	/**choice method:
	 * This procedural method outputs user friendly prompts which provide 3 options for the user to choose from.
	 *
	 * @param none
	 * @throws none	
	 * @return void
	 */
	public void choice() {
		System.out.println("These programs are available. What program would you like to run?");
		System.out.println("1. Permutations (type '1' to run)");
		System.out.println("2. Blob Crusher (type '2' to run)");
		System.out.println("3. Exit the program (type '3' to exit)");
	}//end of choice method
}// end of class Menu