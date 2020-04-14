package Cartoon;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/*
 * Creates a space ship on screen. The methods it has is makeSpaceShip(), setShipLoc(), and getLoc().
 */

public class SpaceShip {

	private Polygon _shipHull;
	private Circle _hullWindow;
	private Rectangle _leftThrust;
	private Rectangle _rightThrust;
	private Polygon _leftFlames;
	private Polygon _rightFlames;
	
	public SpaceShip(Pane spaceShipPane) {
		this.makeSpaceShip(spaceShipPane);
	}
	
	/*
	 * Creates space ship. Takes in a pane as a parameter which the spaceship will be created in.
	 * Doesn't return anything. Makes 6 seperate shapes.
	 */
	
	public void makeSpaceShip(Pane spaceShipPane) {
		_shipHull = new Polygon(0,40,15,0,30,40);
		_shipHull.setFill(Color.GREY);
		_hullWindow = new Circle(15,20,CONSTANTS.WINDOW_RAD);
		_hullWindow.setFill(Color.BLUE);
		_leftThrust = new Rectangle(5,40,CONSTANTS.THRUST_WIDTH,CONSTANTS.THRUST_HEIGHT);
		_leftThrust.setFill(Color.GREY);
		_rightThrust = new Rectangle(17,40,CONSTANTS.THRUST_WIDTH,CONSTANTS.THRUST_HEIGHT);
		_rightThrust.setFill(Color.GREY);
		_leftFlames = new Polygon(5,45,13,45,13,50,9,46,5,50);
		_leftFlames.setFill(Color.ORANGE);
		_rightFlames = new Polygon(17,45,25,45,25,50,21,46,17,50);
		_rightFlames.setFill(Color.ORANGE);
		
		spaceShipPane.getChildren().addAll(_shipHull,_hullWindow,_leftThrust,_rightThrust,
				_leftFlames,_rightFlames);
	}
	
	/*
	 * Sets the ships X location. It does so by taking in a double which specifies how much 
	 * you want to move the ship by. Doesn't return anything. 
	 */
	
	public void setShipLoc(double x) {
		
		_shipHull.setTranslateX(x);
		_hullWindow.setTranslateX(x);
		_leftThrust.setTranslateX(x);
		_rightThrust.setTranslateX(x);
		_leftFlames.setTranslateX(x);
		_rightFlames.setTranslateX(x);
	}
	
	/*
	 * Doesn't take in any parameters. Returns a double which helps determine the location 
	 * of X. Is called in Cartoon.
	 */
	
	public double getLoc() {
		return _shipHull.getTranslateX();
	}

}
