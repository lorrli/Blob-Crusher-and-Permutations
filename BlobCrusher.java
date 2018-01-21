/*=================================================================
Assignment 1: Blob Crusher
Lorraine Li
Mar. 23, 2016
Java, Version 1.7
=================================================================
Problem Definition 	– The user inputs coordinates to guess where the blobs are located. If they guess correctly,
 					  the blob is replaced with '#'. The program continues until the user misses four times or
 					  hits all the blobs.
Input – an integer for x-coordinate, an integer for y-coordinate
Output – the coordinate entered, if the user hit it or not, the game board with the blobs replaced with '#' if hit or displayed normally if missed
Process – if the coordinate has a '*', recursion is used to search around the coordinate entered for any '*' and if found, they are replaced with '#'
=================================================================
List of Identifiers 	-listed in each method
=================================================================
 */ 
import java.io.*;
class BlobCrusher {
	public static int hit; //declares and instantiates an instance variable of type int 
	/**main method:
	 * This procedural method is called automatically and is used to organize the calling of other methods defined in the class.
	 * It also asks for the user to enter their coordinate guesses and displays a message to the user if they hit or missed the blob.
	 * 
	 * List of Local Variables
	 * bc - an object used to get access to the non-static methods defined in the class <type BlobCrusher>
	 * r - the integer value of the number of rows in the array <type int>
	 * c - the integer value of the number of columns in the array  <type int>
	 * board- the array that stores the board for the game <type String[][]>
	 * numString - the String value of the user input of the number <type String>
	 * x - the integer value of the x-coordinate input by user <type int>
	 * y - the integer value of the y-coordinate input by user <type int>
	 * miss - the counter that counts the number of misses <type int>
	 * check - indicates if there are any blobs left in the board <type int>
	 *
	 * @param args <type String>
	 * @throws IO Exception	
	 * @return void
	 */
	public static void main(String[] args) throws IOException {
		BlobCrusher bc = new BlobCrusher();
		int r, c, check;
		r=24;
		c=42;
		String[][] board= new String[r][c];
		int x,y, miss;
		x=y=miss=0;
		bc.instructions();
		board = bc.gameBoard(board, r, c);
		check = bc.findBlobs(board, r, c);
		while (check == 0 && miss<4){
			hit=0;
			bc.displayBoard(board, r, c);
			System.out.println("Please guess the coordinate position of an asterisk in order to erase a blob.");
			x=bc.inputX(x);
			y=bc.inputY(y);
			System.out.println("The coordinate you guessed is: ("+ x+ ","+y+")");
			board = bc.blobCrusher(board,x,y);
			if (hit<1){
				System.out.println("You missed!");
				miss=miss+1;
			}
			else
				System.out.println("You hit it!");
			check = bc.findBlobs(board, r, c);
		} 
		if (miss<4 && check!=0){
			bc.displayBoard(board, r, c);
			System.out.println("You succesfully crushed all blobs! Congratulations");
		}
		else if (miss>=4 && check==0){
			System.out.println("You missed four times. Game Over.");
		}
	}//end of main method
	/**instructions method:
	 * This functional method outputs the instructions to the user. 
	 *
	 * @param none
	 * @throws none
	 * @return void
	 */
	public void instructions(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~INSTRUCTIONS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Hello welcome to the BLOB CRUSHER!");
		System.out.println("This program will display 4 quadrants with 2 blobs in each quadrant.");
		System.out.println("Your job is to enter the coordinates of where you think the blobs are located.");
		System.out.println("If you hit the blob, the entire blob will be replaced with mulitple '#' to indicated you killed the blob.");
		System.out.println("If you hit the blob already, and you hit it again the next time it will count as a miss.");
		System.out.println("If you miss four times, the game will end. Good luck!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//end of instructions method
	
	/**gameBoard method:
	 * This functional method reads from a file and stores it in array. 
	 *
	 * List of Local Variables
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 * line - the string variable that stores the word read per line from the input file  <type String>
	 * i - counter that moves through the array <type int>
	 * j - counter that moves through the array <type int>
	 *
	 * @param board  <type String [][]>, r <type int>, c < type int>
	 * @throws IO Exception	
	 * @return board- the information read from the file that makes up the game board <type String[][]>
	 */
	private String[][] gameBoard(String board[][], int r, int c) throws IOException {
		String line;
		BufferedReader br;
		br=new BufferedReader(new FileReader("E:/blobs5.txt"));
		line=br.readLine();
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){		
				board[i][j]=line;
				line=br.readLine();
			}
		}
		br.close();				
		return board;
	}//end of gameBoard method

	/**getGameBoard method:
	 * This functional method acts as an accessor for the gameBoard method.
	 *
	 * @param board <type String[][]>
	 * @throws IOException	
	 * @return board - the game board  <type String[][]>
	 */
	public String[][] getGameBoard(String board[][]) throws IOException {
		return board;
	}// end of getGameBoard method
	
	/**displayBoard method:
	 * This procedural method prints the game board when called.
	 * 
	 * i - counter used to move through the array <type int>
	 * j - counter used to move through the array <type int>
	 *
	 * @param board - the game board <type String[][]>, r- the number of rows <type int>, c- the number of columns <type int>
	 * @throws none
	 * @return void
	 */
	public void displayBoard(String board[][], int r, int c) {
		System.out.println("Here is the gameboard:");
		for (int i = 0; i<r; i++) {
			for (int j = 0; j<c; j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.println(" ");
		}
	}// end of displayBoard method
	
	/**method inputX
	 * This functional method asks the user to input the x-coordinate of their guess for where the blobs are.
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 * 
	 * @param x: x-coordinate of where the user thinks the blob is <type int>
	 * @return the user's x-coordinate guess <type int>
	 * @throws IO Exception
	 */
	public int inputX(int x)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please input your x coordinate (between 1 and 41)");
		while(true){
			try{
				x=Integer.parseInt(br.readLine());
				break;
			}
			catch(NumberFormatException e){
				System.out.println("This is not a vaild number. Please try again.");
			}
		}
		return x;
	}//end of method inputX
	
	/**method inputY
	 * This functional method asks the user to input the y-coordinate of their guess for where the blobs are.
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 * 
	 * @param y: y-coordinate of where the user thinks the blob is <type int>
	 * @return the user's y-coordinate guess <type int>
	 * @throws IO Exception
	 */
	public int inputY(int y)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please input your y coordinate (between 1 and 21)");
		while(true){
			try{
				y=Integer.parseInt(br.readLine());
				break;
			}
			catch(NumberFormatException e){
				System.out.println("This is not a vaild number. Please try again.");
			}
		}
		return y;
	}//end of method inputY

	/**blobCrusher method:
	 * This functional method uses recursive backtracking to search for asterisks and replaces them with '#'. 
	 *
	 * @param board - the game board <type String[][]>, x- the user's x-coordinate <type int>, the user's y-coordinate <type int>
	 * @throws none	
	 * @return board- the game board <type String[][]>
	 */
	public String[][] blobCrusher(String board[][], int x, int y){
		BlobCrusher bc = new BlobCrusher();
		if (!board[y][x].equals("*")) {
			return board;
		}
		
		else if (board[y][x].equals("*")){
			hit=bc.hit(1);
			board[y][x] = "#";
			blobCrusher(board,x-1,y-1);
			blobCrusher(board,x-1,y);
			blobCrusher(board,x-1,y+1);
			blobCrusher(board,x,y-1);
			blobCrusher(board,x,y+1);
			blobCrusher(board,x+1,y-1);
			blobCrusher(board,x+1,y);
			blobCrusher(board,x+1,y+1);
			return board;
		}
		else
			return board;
	}//end of blobCrusher method
	
	/**hit method:
	 * This functional method uses a counter to count the number of times the user hits the blobs. 
	 *
	 * @param hit- counts the number of hits by the user <type int>
	 * @throws none	
	 * @return hit - the number of hits by the user <type int>
	 */
	public int hit(int hit){
		hit=hit+1;
		return hit;
	}//end of hit method
	
	/**findBlobs method:
	 * This functional method checks whether there are still blobs left in the game board, and sends the answer back. 
	 * 
	 * i - a counter used to travel through the array <type int>
	 * j - a counter used to travel through the array <type int>
	 * r - the integer value of the number of rows in the array <type int>
	 * c - the integer value of the number of columns in the array  <type int>
	 *
	 * @param board - the game board <type String[][]>
	 * @throws none
	 * @return 0 (if has blobs) or 1 (if no blobs)  <type int>
	 */
	public int findBlobs (String board[][], int r, int c) {
		for (int i = 0; i<r; i++) {
			for (int j = 0; j<c; j++){
				if (board[i][j].equals("*"))
					return 0;
			}
		}
		return 1;
	}//end of findBlobs method

}// end of BlobCrusher class
