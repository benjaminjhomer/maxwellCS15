package Cartoon;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
 * This class creates lasers on the screen and moves them up. It has access to the 
 * following methods and classes: incrementPos(), setupTimeline(), and a TimeHandler
 * class.
 */

public class Laser {
	
	private Rectangle _rect;
	private double _y;
	private Pane _pane;
	
	public Laser(Pane pane,double x,double y) {
		_rect = new Rectangle(x,y,CONSTANTS.LASER_W,CONSTANTS.LASER_H);
		_rect.setFill(Color.MEDIUMVIOLETRED);
		_y = _rect.getLayoutY();
		_pane = pane;
		this.setupTimeline();
		pane.getChildren().add(_rect);
	}
	
	/*
	 * Sets up timeline for laser to move it up. Takes in no parameters, returns 
	 * nothing. It goes till it hits an enemy or the end of the pane.
	 */
	
	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(CONSTANTS.DUR),new TimeHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	/*
	 * This method increments the laser's position up the screen and checks to 
	 * see if it has reached the end of the pane. It takes no parameters and returns 
	 * nothing.
	 */
	
	public void incrementPos() {
		boolean pos = (_y > CONSTANTS.OUT_OF_BOUNDS);
		if (pos) {
			_rect.setLayoutY(_y-CONSTANTS.LASER_SPEED);
			_y = _y-CONSTANTS.LASER_SPEED;
		} else {
			_pane.getChildren().removeAll(_rect);
		}
	}
	
	/*
	 * This class moves the lasers down the screen and removes them from the pane when 
	 * they reach the top.
	 */
	
	private class TimeHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent event) {
			incrementPos();
			
		}
	}
}
