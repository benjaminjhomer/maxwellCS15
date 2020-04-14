package Cartoon;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

/*
 * Creates an enemy ship on screen and makes it move down. Can use setPos(),getXPos(),
 * getYPos(), and setupTimeLine.
 */

public class Enemy {
	
	private Polygon _shipHull;
	private Circle _shipWindow;
	private Polygon _flames;
	private Label _loc;
	private double _y;
	private double _x;
	
	public Enemy(Pane enemyPane, Label loc,double x, double y) {
		_shipHull = new Polygon(0,5,20,5,10,35);
		_shipHull.setFill(Color.PURPLE);
		_shipWindow = new Circle(10,15,CONSTANTS.WINDOW_RAD);
		_shipWindow.setFill(Color.RED);
		_flames = new Polygon(0,0,0,5,20,5,20,0,15,4,10,0,5,4);
		_flames.setFill(Color.ORANGE);
		this.setPos(x, y);
		_y= y;
		_x = x;
		_loc = loc;
		enemyPane.getChildren().addAll(_shipHull,_shipWindow,_flames);
		this.setupTimeline();
	}
	/*
	 * setPos takes in two parameters which are of double type. When the method is called, 
	 * it sets the position of the Spaceship to the location defined by the X and Y coordinates.
	 */
	public void setPos(double x, double y) {
		_shipHull.setLayoutX(x);
		_shipWindow.setLayoutX(x);
		_flames.setLayoutX(x);
		_shipHull.setLayoutY(y);
		_shipWindow.setLayoutY(y);
		_flames.setLayoutY(y);
	}
	
	/*
	 * getPos returns the ships x-location in double type. 
	 */
	public double getXPos() {
		return _shipHull.getLayoutX();
	}
	
	/*
	 * getPos returns the ships x-location in double type. 
	 */
	public double getYPos() {
		return _shipHull.getLayoutY();
	}
	
	/*
	 * This Timeline repeats itself every .01 seconds and uses the private class TimeHandler.
	 * It goes indefinitely until TimeHandler removes the object.
	 */
	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(CONSTANTS.DUR),new TimeHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	/*
	 * This private class is iterated every .01 second when called upon by setupTimeline.
	 * It checks the location of the enemy ship and if the enemy ship is between a point \
	 * where it is close, it makes a label say ENEMY NEAR. If it is past 290, then it is game over.
	 * It moves the enemy ship .2 pixels down every time it is called.
	 */
	private class TimeHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent event) {
			
			if (_y > CONSTANTS.GAME_OVER) {
				_loc.setText(" GAME OVER");
				
			} else if (_y>CONSTANTS.WARNING && _y<CONSTANTS.GAME_OVER) {
				_loc.setText("ENEMY NEAR");
				_y += CONSTANTS.BASE_SPEED;
				setPos(_x,_y);
				
			} else {
				_y += CONSTANTS.BASE_SPEED;
				setPos(_x,_y);
			}
		}
	}

}
