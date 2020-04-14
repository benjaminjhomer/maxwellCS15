package DoodleJump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * This class creates the ledges. Its methods are setPosition(), getShapes(), getYPosition(), and getXPosition().
 */

public class Ledge {

	private Rectangle _rect;

	public Ledge(Pane pane) {
		_rect = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
		_rect.setFill(Color.FORESTGREEN);
		pane.getChildren().add(_rect);
	}

	/*
	 * This method sets the position of the _rect. It takes in two parameters, each
	 * of which are doubles. The first is the x position and second is the y
	 * position. It returns nothing.
	 */

	public void setPosition(double x, double y) {
		_rect.setX(x);
		_rect.setY(y);
	}
	
	//This is a getter for the node of the _rect.

	public Rectangle getShapes() {
		return _rect;
	}
	
	//This is a getter for the Y position of the _rect.

	public double getYPosition() {
		return _rect.getY();
	}
	
	//This is a getter for the X position of the _rect.
	
	public double getXPosition() {
		return _rect.getX();
	}

}
