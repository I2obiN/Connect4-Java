//Connect 4 window that appears in each cell in the board

//imports required for this class
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

// class definition 
class Window extends Group {
	
	// private fields of the class
	private Ellipse e; 			// ellipse for rendering this window
	private Translate pos; 		//translate to set the position of this window

	// constructor for the class
	public Window() {
		// make a new Ellipse and Translate, add the Translate to the Ellipse, add the Ellipse to the Group 
		pos = new Translate();
		e = new Ellipse();
		e.setFill(Color.WHITE);
		getChildren().addAll(e);
		e.getTransforms().add(pos);
		e.setFill(Color.WHITE);
	}

	// overridden version of the resize method
	@Override
	public void resize(double width, double height) {
		// call the super class method and update the centre and radius of the ellipse representing the window 
		super.resize(width, height);
		e.setCenterX(width / 2);
		e.setCenterY(height / 2);
		e.setRadiusX(width / 2);
		e.setRadiusY(height / 2);
	}

	// overridden version of the relocate method
	@Override
	public void relocate(double x, double y) {
		// call the superclass method and update the relevant transform
		super.relocate(x, y);
		pos.setX(x);
		pos.setY(y);
	}
}
