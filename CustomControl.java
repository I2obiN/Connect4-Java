/* Custom control for the Connect 4 board 

 * This will have a CustomControlSkin & a Board
 * The control will detect mouse and keyboard interactions and overlay the board 
*/

//imports for the class
import javafx.event.*;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

//class definition
class CustomControl extends Control {
	//private fields of the class
	private Board connect4Board;	// a Connect 4 board
	private ToolBar toolbar1; // Added a toolbar for about and reset buttons
	private Button aboutbtn;
	private Button resetbtn;
	//constructor 
	public CustomControl() {
		// set a default skin, generate a board and add the board to the control
		toolbar1 = new ToolBar();
		aboutbtn = new Button("About");
		resetbtn = new Button("Reset");
		toolbar1.getItems().addAll(aboutbtn, resetbtn);
		toolbar1.setOrientation(Orientation.HORIZONTAL);
		// Needs to be changed, probably something that can snap it to the top of the window, will resize width np, but couldn't get it to remain at the top
		toolbar1.setTranslateY(-290);
		setSkin(new CustomControlSkin(this));
		connect4Board = new Board();
		getChildren().addAll(connect4Board, toolbar1);
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 6; j++){
				connect4Board.placeWindow(i, j);
			}
		}
		
		// add windows
		//connect4Board.placeWindows();
		
		// mouse clicked event handler that will try to place a piece on the board
		setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				connect4Board.placePiece(event.getX());
			}
		});
				
		// key stroke event handler which will call the restGame method when the space bar is pressed
		setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE)
					connect4Board.resetGame();
			}
		});
		
		// reset btn
		resetbtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				connect4Board.resetGame();
			}
		});
		
		aboutbtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				System.out.println("\nConnect 4 by -- Alex Cronin and Thomas Hood -- Griffith College Dublin 20/11/2015 -- BScH Year 3 Computer Science");
			}
		});
		
	}

	//override the resize method
	@Override
	public void resize(double width, double height) {
		// call the super class method and update the size of the board
		super.resize(width, height);
		connect4Board.resize(width, height);
	}
}
