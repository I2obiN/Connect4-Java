//Connect 4 application

// imports
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

// class definition
public class Connect4 extends Application {
	// private fields for this class
	private StackPane sp_mainlayout;
	private CustomControl cc_custom;	//control which has a board and detects mouse and keyboard events
	
	// overridden init method
	@Override
	public void init() {
		// initialize the layout, create a CustomControl and it to the layout
		sp_mainlayout = new StackPane();
		cc_custom = new CustomControl();
		sp_mainlayout.getChildren().add(cc_custom);
	}
	// overridden start method
	@Override
	public void start(Stage primaryStage) {
		// set the title and scene, and show the stage
		primaryStage.setTitle("Connect 4");
		primaryStage.setScene(new Scene(sp_mainlayout, 700, 600));
		primaryStage.show();
		primaryStage.setResizable(false); // Made it non-resizable because the toolbar can't seem to snap to the top of the scene/window
	}

	// overridden stop method
	@Override
	public void stop() {
	}
	
	// entry point into our program to launch our JavaFX application
	public static void main(String[] args) {
		launch(args);
	}
}
