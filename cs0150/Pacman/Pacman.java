package Pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * This class is contained within PaneOrganizer and associated with GameLogic. It has 
 * access to three methods: getNode(), setDirection(), and getCurrent(). 
 */

public class Pacman {

	private Rectangle _pac;
	private Direction _current;

	public Pacman() {
		_pac = new Rectangle(Constants.PAC_SIZE, Constants.PAC_SIZE);
		_pac.setFill(Color.YELLOW);
		_current = Direction.UP;
	}

	// This method has no parameters and returns the Rectangle _pac.

	public Rectangle getNode() {
		return _pac;
	}

	/*
	 * This method has a Direction parameter and returns nothing. It sets _current
	 * to that direction.
	 */

	public void setDirection(Direction d) {
		_current = d;
	}

	// This method has no parameters and returns the Direction value of _current.

	public Direction getCurrent() {
		return _current;
	}
}