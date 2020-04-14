package Pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * This class implements the interface of Interactable. It has access to 
 * getDirection(), setDirection(), setColor(), getNode(), isType() and 
 * interacted(). It is contained within PaneOrganizer and associated with 
 * its corresponding Tile.
 */

public class Ghost implements Interactable {
	private Rectangle _gh;
	private Direction _current;
	private int _offX;
	private int _offY;

	public Ghost(Color color, int x, int y) {
		_gh = new Rectangle(Constants.PAC_SIZE, Constants.PAC_SIZE);
		_gh.setFill(color);
		_current = Direction.UP;
		_offX = x;
		_offY = y;
	}

	// This method has no parameters and returns the Rectangle _gh.

	public Rectangle getNode() {
		return _gh;
	}

	// This method has no parameter and returns the enum Consumable.GHOST.

	public Consumable isType() {
		return Consumable.GHOST;
	}

	// This method has no parameters and returns the Integer value of _offX

	public int getOffX() {
		return _offX;
	}

	// This method has no parameters and returns the Integer value of _offY.
	
	public int getOffY() {
		return _offY;
	}

	/*
	 * This method has no parameter and returns nothing. It does nothing because
	 * when the
	 */
	
	public void interacted() {

	}

	// This method has no parameters and returns the Direction value of _current.

	public Direction getDirection() {
		return _current;
	}

	/*
	 * This method takes in a Direction as a parameter and returns nothing. It sets
	 * _current to the Direction passed in as a parameter.
	 */

	public void setDirection(Direction d) {
		_current = d;
	}

	/*
	 * This method takes in a Color as a parameter and returns nothing. It sets the
	 * color of _gh to the Color passed in as a parameter.
	 */

	public void setColor(Color c) {
		_gh.setFill(c);
	}
}
