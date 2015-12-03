// Connect4 board and the game logic

// imports necessary for this class
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// class definition 
class Board extends Pane {
	
	//private fields of the class
	private int boardWidth = 7;			// the width of the Connect4 board
	private int boardHeight = 6;		// the height of the Connect4 board
	private int[][] board; 				// 2D array that holds  int values representing the pieces
	private Piece[][] pieces; 			// 2D array that holds the renders of the pieces
	private Window[][] windows; 		// 2D array that holds all the windows (white circles) for the board 
	private Rectangle background; 		// background of the board
	private double cell_width;			// width of a cell in the board
	private double cell_height; 		// height of a cell in the board
	private int current_player; 		// hold the value of the current player (PlayerRed or Player Yellow)
	boolean flag = true;				// winner flag
	boolean drawflag = false;			// draw flag
	private int turns = 0;

	// constants to be inserted into the 2D board array to keep track of the location of cells containing 
	// empty, red and yellow pieces 
	private final int EMPTY = 0;		// 0 is used to indicate that a cell in the board is unoccupied
	private final int PlayerRed = 1;	// 1 is used to indicate that a cell in the board is occupied by a red piece
	private final int PlayerYellow = 2; // 2 is used to indicate that a cell in the board is occupied by a yellow piece
	
	int winner = 0;						// variable to determine who the current winner is: 0 - no current winner, 
										// 1 - red is the current winner, 2 - yellow is the current winner


	// constructor for the class
	public Board() {
		// initialize the background of the board and add it to the board
		board = new int[boardWidth][boardHeight];
		pieces = new Piece[boardWidth][boardHeight];
		windows = new Window[boardWidth][boardHeight];
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 6; j++){
				board[i][j] = EMPTY;
				windows[i][j] = new Window();
				pieces[i][j] = null;
			}
		}
		
		current_player = PlayerRed;
		
		background = new Rectangle();
		background.setFill(Color.BLUE);
		
		getChildren().addAll(background);

		// initialize board array to the correct size .. done
 
		// initialize pieces array to the correct size .. done
		 
		// initialize windows array to the correct size .. done
		 
		// for loop to populate all arrays to default values and add the windows to the board .. done		 
		 
		// set the current player to red .. done
		 
	}

	// override resize method to ensure board appears correctly
	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		cell_width = width / 7;
		cell_height = height / 6;
		
		background.setWidth(width);
		background.setHeight(height);
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 6; j++) {
				if(board[i][j] != 0){
					pieces[i][j].relocate(i * cell_width, j * cell_height);
					pieces[i][j].resize(cell_width, cell_height);
				}
				else {
					windows[i][j].resize(cell_width, cell_height);
					windows[i][j].relocate(i * cell_width, j * cell_height);
				}
			}
		}
		
		//call the superclass resize method  

		// resize the rectangle to take the full size of the widget 
		
		// calculate the width and height of a cell in which a windows and a piece will sit		 

		// nested for loop to reset the sizes and positions of all pieces that were already placed 
		// and update the position of the windows in the board 
	}

	// method for resetting the game
	public void resetGame() {
		board = new int[boardWidth][boardHeight];
		pieces = new Piece[boardWidth][boardHeight];
		windows = new Window[boardWidth][boardHeight];
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 6; j++){
				board[i][j] = EMPTY;
				windows[i][j] = new Window();
				pieces[i][j] = null;
				getChildren().addAll(windows[i][j]);
			}
		}
		
		current_player = PlayerRed;
		winner = 0;
		turns = 0;
		flag = true;
		drawflag = false;
		System.out.print("Game Reset! Next game ...\n");
	}
	
	public void placeWindow(final int i, final int j){
			if(board[i][j] == EMPTY) {
				windows[i][j].resize(cell_width, cell_height);
				windows[i][j].relocate(i * cell_width, j * cell_height);
				getChildren().add(windows[i][j]);
			}
	}

	// method that tries to place a piece in the board
	public void placePiece(final double x) {
		// calculate what column the piece should be placed in 
		int indexx = (int)(x / cell_width);
		int indexy = (int)(0);
		
		// for loop to find the next available row in that column
			for(int j = 0; j < 6; j++) {
				if(board[indexx][j] == EMPTY){
					indexy = j;
				}
			}
		
		// if the available row is less than the height of the board proceed
			
		// call the winner method to see if there is a winner
		if(drawflag != true){
		if(turns >= 41){System.out.println("Draw Game!"); drawflag = true;}
		winner(indexx, indexy);
		
		/* if the current player is red
		   put the int value corresponding to the red piece in the relevant position in the board array 
		   put a red piece in the relevant position in the pieces array and resize and relocate the piece
		   add the piece to the board 
		   change the current player to yellow
		 */
		
		if(board[indexx][indexy] == EMPTY && current_player == PlayerRed && winner != 1 && winner != 2 && drawflag != true) {
			board[indexx][indexy] = PlayerRed;
			pieces[indexx][indexy] = new Piece(PlayerRed);
			pieces[indexx][indexy].resize(cell_width, cell_height);
			pieces[indexx][indexy].relocate(indexx * cell_width, indexy * cell_height);
			getChildren().add(pieces[indexx][indexy]);
			turns++;
			current_player = PlayerYellow;
		}
		
		/* else if current player is yellow
		   put the int value corresponding to the yellow piece in the relevant position in the board array 
		   put a yellow piece in the relevant position in the pieces array and resize and relocate the piece
		   add the piece to the board 
		   change the current player to red
		 */
		
		if(board[indexx][indexy] == EMPTY && current_player == PlayerYellow && winner != 1 && winner != 2 && drawflag != true) {
			board[indexx][indexy] = PlayerYellow;
			pieces[indexx][indexy] = new Piece(PlayerYellow);
			pieces[indexx][indexy].resize(cell_width, cell_height);
			pieces[indexx][indexy].relocate(indexx * cell_width, indexy * cell_height);
			getChildren().add(pieces[indexx][indexy]);
			turns++;
			current_player = PlayerRed;
		}
		
		if (winner == 1 && drawflag != true || winner == 2 && drawflag != true) {
			if(winner == 1 && drawflag != true){
			System.out.println("Red is a winner!");
			if(flag == true && drawflag == false){
			board[indexx][indexy] = PlayerRed;
			pieces[indexx][indexy] = new Piece(PlayerRed);
			pieces[indexx][indexy].resize(cell_width, cell_height);
			pieces[indexx][indexy].relocate(indexx * cell_width, indexy * cell_height);
			getChildren().add(pieces[indexx][indexy]);}
			flag = false;
			}
			else if(winner == 2 && drawflag != true){
			System.out.println("Yellow is a winner!");
			if(flag == true && drawflag == false){
			board[indexx][indexy] = PlayerYellow;
			pieces[indexx][indexy] = new Piece(PlayerYellow);
			pieces[indexx][indexy].resize(cell_width, cell_height);
			pieces[indexx][indexy].relocate(indexx * cell_width, indexy * cell_height);
			getChildren().add(pieces[indexx][indexy]);}
			flag = false;
			}
		}
		}
	if(drawflag == true){resetGame();}
	}

		/* method to determine who the current winner is and display a message (additional variable and/or
		   updates to the layout may be required) - this method should only be attempted on completion of
		   the other steps
		 */
		public int winner(int indexx , int indexy) {
		if(current_player == PlayerYellow){
			int countverticalaboveyellow = 1;
			int countverticalbelowyellow = 1;
			int counthorizontalleftyellow = 1;
			int counthorizontalrightyellow = 1;
			int countdiagonalleftrighthighyellow = 1;
			int countdiagonalleftrightlowyellow = 1;
			int countdiagonalrightlefthighyellow = 1;
			int countdiagonalrightleftlowyellow = 1;
			
			// Check for yellow winner above/below
						for(int j = indexy-1; j != -1; j--){
							if(board[indexx][j] == PlayerYellow){
								countverticalaboveyellow++;
							}
							if(board[indexx][j] == PlayerRed && countverticalaboveyellow != 4 || board[indexx][j] == EMPTY && countverticalaboveyellow != 4){
								countverticalaboveyellow=0;
							}
						}
						
						// below
						for(int j = indexy+1; j != 6; j++){
							if(board[indexx][j] == PlayerYellow){
								countverticalbelowyellow++;
							}
							if(board[indexx][j] == PlayerRed && countverticalbelowyellow != 4 || board[indexx][j] == EMPTY && countverticalbelowyellow != 4){
								countverticalbelowyellow=0;
							}
						}
						
						// Check for yellow winner horizontally, left
						for(int j = indexx-1; j != -1; j--){
							if(board[j][indexy] == PlayerYellow){
								counthorizontalleftyellow++;
							}
							if(board[j][indexy] == PlayerRed && counthorizontalleftyellow != 4 || board[j][indexy] == EMPTY && counthorizontalleftyellow != 4){
								counthorizontalleftyellow=0;
							}
						}
						
						// right
						for(int j = indexx+1; j <= 6; j++){
							if(board[j][indexy] == PlayerYellow){
								counthorizontalrightyellow++;
							}
							if(board[j][indexy] == PlayerRed && counthorizontalrightyellow != 4 || board[j][indexy] == EMPTY && counthorizontalrightyellow != 4){
								counthorizontalrightyellow=0;
							}
						}
						
						// Check for yellow diagonal winner right to left, high and low
						for(int x = indexx-1, j = indexy-1; x >= 0 && j >= 0; x--, j--){
								if(j == -1){j = 0;} // Bug fix
								if(board[x][j] == PlayerYellow){
									countdiagonalrightlefthighyellow++;
								}
								if(board[x][j] == PlayerRed && countdiagonalrightlefthighyellow != 4 || board[x][j] == EMPTY && countdiagonalrightlefthighyellow != 4){
									countdiagonalrightlefthighyellow=0;
								}
						}
						
						//low
						for(int x = indexx+1, j = indexy+1; x <= 6 && j <= 5; x++, j++){
							if(x == -1){x = 0;} // Bug fix
								if(board[x][j] == PlayerYellow){
									countdiagonalrightleftlowyellow++;
								}
								if(board[x][j] == PlayerRed && countdiagonalrightleftlowyellow != 4 || board[x][j] == EMPTY && countdiagonalrightleftlowyellow != 4){
									countdiagonalrightleftlowyellow=0;
								}
						}
						
						// Check for yellow diagonal winner left to right, high and low
						for(int x = indexx+1, j = indexy-1; x <= 6 && j > 0; x++, j--){
							if(j == -1){j = 0;} // Bug fix
							if(board[x][j] == PlayerYellow){
								countdiagonalleftrighthighyellow++;
							}
							if(board[x][j] == PlayerRed && countdiagonalleftrighthighyellow != 4 || board[x][j] == EMPTY && countdiagonalleftrighthighyellow != 4){
								countdiagonalleftrighthighyellow=0;
							}
						}
						
						//low
						for(int x = indexx-1, j = indexy+1; x >= 0 && j <= 5; x--, j++){
							if(board[x][j] == PlayerYellow){
								countdiagonalleftrightlowyellow++;
							}
							if(board[x][j] == PlayerRed && countdiagonalleftrightlowyellow != 4 || board[x][j] == EMPTY && countdiagonalleftrightlowyellow != 4){
								countdiagonalleftrightlowyellow=0;
							}
						}
						
						if(countverticalaboveyellow >= 4 || countverticalbelowyellow >= 4 || counthorizontalleftyellow >= 4 || counthorizontalrightyellow >= 4 || countdiagonalrightlefthighyellow >= 4 || countdiagonalrightleftlowyellow >= 4 || countdiagonalleftrighthighyellow >= 4 || countdiagonalleftrightlowyellow >= 4){
							winner = 2;
							countverticalaboveyellow = 1;
							countverticalbelowyellow = 1;
							counthorizontalleftyellow = 1;
							counthorizontalrightyellow = 1;
							countdiagonalleftrighthighyellow = 1;
							countdiagonalleftrightlowyellow = 1;
							countdiagonalrightlefthighyellow = 1;
							countdiagonalrightleftlowyellow = 1;
						}	
		}
			
		else if(current_player == PlayerRed) {
			
			int countverticalabovered = 1;
			int countverticalbelowred = 1;
			int counthorizontalleftred = 1;
			int counthorizontalrightred = 1;
			int countdiagonalleftrighthighred = 1;
			int countdiagonalleftrightlowred = 1;
			int countdiagonalrightlefthighred = 1;
			int countdiagonalrightleftlowred = 1;
			
			// Check for red winner above/below
			for(int j = indexy-1; j >= 0; j--){
				if(board[indexx][j] == PlayerRed){
					countverticalabovered++;
				}
				if(board[indexx][j] == PlayerYellow && countverticalabovered != 4 || board[indexx][j] == EMPTY && countverticalabovered != 4){
					countverticalabovered=0;
				}
			}
			
			// below
			for(int j = indexy+1; j != 6; j++){
				if(board[indexx][j] == PlayerRed){
					countverticalbelowred++;
				}
				if(board[indexx][j] == PlayerYellow && countverticalbelowred != 4 || board[indexx][j] == EMPTY && countverticalbelowred != 4){
					countverticalbelowred=0;
				}
			}
			
			// Check for red winner horizontally, right
			for(int j = indexx-1; j >= 0; j--){
				if(board[j][indexy] == PlayerRed){
					counthorizontalleftred++;
				}
				if(board[j][indexy] == PlayerYellow && counthorizontalleftred != 4 || board[j][indexy] == EMPTY && counthorizontalleftred != 4){
					counthorizontalleftred=0;
				}
			}
			
			// left
			for(int j = indexx+1; j <= 6; j++){
				if(board[j][indexy] == PlayerRed){
					counthorizontalrightred++;
				}
				if(board[j][indexy] == PlayerYellow && counthorizontalrightred != 4 || board[j][indexy] == EMPTY && counthorizontalrightred != 4){
					counthorizontalrightred=0;
				}
			}
			
			// Check for red diagonal winner right to left, high and low
			for(int x = indexx-1, j = indexy-1; x >= 0 && j >= 0; x--, j--){
					if(j == -1){j = 0;} // Bug fix
					if(board[x][j] == PlayerRed){
						countdiagonalrightlefthighred++;
					}
					if(board[x][j] == PlayerYellow && countdiagonalrightlefthighred != 4 || board[x][j] == EMPTY && countdiagonalrightlefthighred != 4){
						countdiagonalrightlefthighred=0;
					}
			}
			
			//low
			for(int x = indexx+1, j = indexy+1; x <= 6 && j <= 5; x++, j++){
					if(board[x][j] == PlayerRed){
						countdiagonalrightleftlowred++;
					}
					if(board[x][j] == PlayerYellow && countdiagonalrightleftlowred != 4 || board[x][j] == EMPTY && countdiagonalrightleftlowred != 4){
						countdiagonalrightleftlowred=0;
					}
			}
			
			// Check for red diagonal winner left to right, high and low
			for(int x = indexx+1, j = indexy-1; x <= 6 && j > 0; x++, j--){
				if(j == -1){j = 0;} // Bug fix
				if(board[x][j] == PlayerRed){
					countdiagonalleftrighthighred++;
				}
				if(board[x][j] == PlayerYellow && countdiagonalleftrighthighred != 4 || board[x][j] == EMPTY && countdiagonalleftrighthighred != 4){
					countdiagonalleftrighthighred=0;
				}
			}
			
			//low
			for(int x = indexx-1, j = indexy+1; x >= 0 && j <= 5; x--, j++){
				if(board[x][j] == PlayerRed){
					countdiagonalleftrightlowred++;
				}
				if(board[x][j] == PlayerYellow && countdiagonalleftrightlowred != 4 || board[x][j] == EMPTY && countdiagonalleftrightlowred != 4){
					countdiagonalleftrightlowred=0;
				}
			}
			
			if(countverticalabovered >= 4 || countverticalbelowred >= 4 || counthorizontalleftred >= 4 || counthorizontalrightred >= 4 || countdiagonalrightlefthighred >= 4 || countdiagonalrightleftlowred >= 4 || countdiagonalleftrighthighred >= 4 || countdiagonalleftrightlowred >= 4){
				winner = 1;
				countverticalabovered = 1;
				countverticalbelowred = 1;
				counthorizontalleftred = 1;
				counthorizontalrightred = 1;
				countdiagonalleftrighthighred = 1;
				countdiagonalleftrightlowred = 1;
				countdiagonalrightlefthighred = 1;
				countdiagonalrightleftlowred = 1;
			}	
		}
		return winner;
	}
}
