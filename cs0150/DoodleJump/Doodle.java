package DoodleJump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/*
 * This class has all the information needed to make a Doodle. Its methods are setPostion(), 
 * getYPosition(), getXPosition(),getShapes(), setVel(), updateVel(), updateLoc(), getVel(),
 * and remove().
 */

public class Doodle {

	private Rectangle _rect;
	private double _vel;
	private double _xLoc;
	private double _yLoc;
	private Ellipse _eyeR;
	private Ellipse _eyeL;
	private Pane _pane;

	public Doodle(Pane pane) {
		_pane = pane;
		_rect = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
		_rect.setFill(Color.BLUE);
		_eyeR = new Ellipse(3, 5);
		_eyeR.setFill(Color.GRAY);
		_eyeL = new Ellipse(3, 5);
		_eyeL.setFill(Color.GRAY);
		_pane.getChildren().addAll(_rect, _eyeR, _eyeL);
		this.setPosition(Constants.DOOD_START_X, Constants.DOOD_START_Y);
		_xLoc = this.getXPosition();
		_yLoc = this.getYPosition();
		_vel = Constants.REBOUND_VELOCITY;
	}

	/*
	 * This method sets the position of the _rect, _eyeR, and eyeL. It takes in two
	 * doubles, the x coordinate and the y coordinate. It returns nothing.
	 */

	public void setPosition(double x, double y) {
		_xLoc = x;
		_yLoc = y;
		_rect.setX(x);
		_rect.setY(y);
		_eyeR.setCenterX(x + 5);
		_eyeL.setCenterX(x + 15);
		_eyeR.setCenterY(y + 8);
		_eyeL.setCenterY(y + 8);
	}

	// This is a getter for the Y position of the _rect.

	public double getYPosition() {
		return _rect.getY();
	}

	// This is a getter for the X position of the _rect.

	public double getXPosition() {
		return _rect.getX();
	}

	// This is a getter for the node of the _rect.

	public Rectangle getShapes() {
		return _rect;
	}

	// Sets _vel to the double it takes in as a double. Returns nothing.

	public void setVel(double newVel) {
		_vel = newVel;
	}

	/*
	 * Updates the _vel based on gravity and the duration. No parameters and returns
	 * nothing.
	 */

	public void updateVel() {
		_vel += (Constants.GRAVITY * Constants.DURATION);
	}

	/*
	 * Updates the _yLoc based on _vel and the duration. No parameters and returns
	 * nothing.
	 */

	public void updateLoc() {
		_yLoc += (_vel * Constants.DURATION);
		setPosition(_xLoc, _yLoc);
	}

	// This is a getter for the _vel of the _rect.

	public double getVel() {
		return _vel;
	}

	/*
	 * Removes all shapes from the parent pane. No parameters and returns nothing.
	 */

	public void remove() {
		_pane.getChildren().removeAll(_rect, _eyeR, _eyeL);
	}
}