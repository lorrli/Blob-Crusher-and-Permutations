/*=================================================================
Assignment 1: Permutations
Lorraine Li
Mar. 23, 2016
Java, Version 1.7
=================================================================
Problem Definition 	– accepts a command line parameter n and prints out all n! permutations 
					  of the n letters starting at the letter a, assuming that n is no greater than 26.
Input – a number between 1 and 26
Output – the n! permutations of that number of letters starting with the letter a
Process – uses recursion to swap the letters of the string around and stores them in an array list
=================================================================
 */ 

import java.util.ArrayList;
import java.io.*;
class Perm1 {
	
	/**main method:
	 * This procedural method is called automatically and is used to organize the calling of other methods defined in the class
	 * It also displays UFP's that tell the user the instructions for the program.
	 * 
	 * List of Local Variables
	 * n - the value the user enters (between 1-26) <type int>
	 * factorial - stores the value of n! <type double>
	 * p1 - an object used to get access to the non-static methods defined in the class <type Perm1>
	 * alphabet - the String value that stores all the letters of the alphabet <type String>
	 * run - the String value that the user enters to decide to run the program or not <type String>
	 * result - the array list that contains all the permutations <type ArrayList<String>>
	 *
	 * @param args <type String>
	 * @throws IO Exception	
	 * @return void
	 */
	public static void main(String[] args) throws IOException  {
		double factorial;
		int n;
		String alphabet,run;
		n=0;
		alphabet=("abcdefghijklmnopqrstuvwxyz");
		Perm1 p1= new Perm1();
		ArrayList<String> result = new ArrayList<String>();
		System.out.println("Hello welcome to the Letter Combo.");
		System.out.println("This program allows you to enter a number (n), and it finds all the n! permutations of that number of letters starting with the letter a.");
		System.out.println("");
		System.out.println("Would you like to begin? Type anything to continue or 'end' to exit.");
		run= p1.inputContinue();
		while (!run.equalsIgnoreCase("end")){
			System.out.println("Ok let's begin.");
			System.out.println("Please enter a number between 1 and 26.");
			n=p1.inputMain();
			factorial= p1.findFactorial(n);
			System.out.println("The factorial of your number is "+ factorial+". So you will have "+ factorial +" permutations.");
			if ((n < 1) || (n > 26)) {
				System.out.print("Invalid input!");
			} 
			else {
				result = doPerm(alphabet.substring(0, n));
				for(String element : result){
					System.out.println(element + "  ");
				}    		
			}
			System.out.println("Thanks for using my program! Would you like to run it again? Type anything to continue or 'end' to exit.");
			run=p1.inputContinue();
		}
	}//end of main method
	
	/**method inputContinue
	 * This functional method accepts the user's answers to continue the program or terminate it. 
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 * run- the String value that the user enters to decide to run the program or not <type String>
	 * 
	 * @param none
	 * @return run <type String>
	 * @throws IO Exception
	 */
	public String inputContinue()throws IOException{
		String run;
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		run= br.readLine();
		return run;
	}//end of method inputContinue
	
	/**method inputMain
	 * This functional method accepts the user's input of a number between 1 and 26. 
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 * n- the value the user enters (between 1-26) <type int>
	 * 
	 * @param none
	 * @return n <type int>
	 * @throws IO Exception
	 */
	public int inputMain()throws IOException{
		int n;
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n= Integer.parseInt(br.readLine());
		return n;
	}// end of method inputMain
	
	/**method findFactorial:
	 * This functional method finds the factorial of the number the user inputs and returns it.
	 * 
	 * @param n- the value the user enters (between 1-26) <type int>
	 * @return 1 (base case: if n=1 or n=0), otherwise returns and calls the method again of n times findFactorial(n-1)
	 * @throws IO Exception
	 */
	public double findFactorial(double n ){
		if ((n == 1)  || (n == 0)  )
			return 1;
		else
			return (n* findFactorial (n-1));
	}//end of method findFactorial
	
	/**doPerm method:
	 * This functional method reads user input, and returns the value 
	 *
	 * List of Local Variables
	 * result - the array list that contains all the permutations <type ArrayList<String>>
	 * partialAlphabet - the array list that contains the 'n' number of letters as specified by the user <type ArrayList<String>>
	 * currentChar - initially the first letter of the alphabet, is then swapped with the next letters to allow the permutations to occur  <type String>
	 * resultStr - the sequence of letters after being swapped <type String>
	 * currentStr - the letter that gets swapped <type String>
	 *
	 * @param myAlphabet- all the letters of the alphabet <type String>
	 * @throws none
	 * @return result - the array list that contains all the permutations <type ArrayList<String>>
	 */
	public static ArrayList<String> doPerm(String myAlphabet) {
		ArrayList<String> result = new ArrayList<String>();	
		ArrayList<String> partialAlphabet = new ArrayList<String>();
		String currentChar, resultStr;
		if (myAlphabet.length() <= 1) {
			result.add(myAlphabet);	
		} 
		else {		
			currentChar = myAlphabet.substring(0, 1);
			partialAlphabet = doPerm(myAlphabet.substring(1));			
			for (String currentStr : partialAlphabet) { 
				for (int j = 0; j <= currentStr.length(); j++) {
					resultStr = currentStr.substring(0, j) + currentChar + currentStr.substring(j);
					result.add(resultStr);
				}
			}		
		}
		return result;
	}//end of doPerm method
}//end of Perm1 class 

