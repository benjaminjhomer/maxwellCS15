package DoodleJump;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/*
 * This class handles all the logic of the game. Its inner classes are KeyHandler, 
 * TimeHandler, and RestartHandler. Its methods are setInstVar(), setupTimeline(), 
 * and two getters for KeyHandler and TimeHandler.
 */

public class Game {

	private PaneOrganizer _organizer;
	private BorderPane _root;
	private Pane _jumpSpace;
	private Label _gameState;
	private VBox _bottomPane;
	ArrayList<Ledge> _ledge = new ArrayList<Ledge>();
	private Doodle _dood;
	private int _score;
	private Label _scoreLabel;
	private Timeline _timeline;
	private Button _rstBtn;

	public Game(PaneOrganizer organizer, BorderPane root) {
		_organizer = organizer;
		_root = root;
	}

	/*
	 * This method gets the necessary instance variables from PaneOrganizer and
	 * assigns the respective instance variables to the ones in PaneOrganizer. It
	 * has no parameters and does not return anything.
	 */

	public void setInstVar() {
		_bottomPane = _organizer.getBPane();
		_jumpSpace = _organizer.getJS();
		_ledge = _organizer.getLedge();
		_score = _organizer.getScore();
		_scoreLabel = _organizer.getSL();
		_gameState = _organizer.getGS();
		_dood = _organizer.getDood();
	}

	/*
	 * This method sets up the timeline that will handle platform generation,
	 * platform removal, the doodle's vertical movement, and when to end the game.
	 * It has no parameters and returns nothing.
	 */

	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}

	// Getter for the KeyHandler which deals with the doodle's X-movement.

	public EventHandler<KeyEvent> getKeyHandler() {
		return new KeyHandler();
	}

	/*
	 * Getter for the timeline that will handle platform generation, platform
	 * removal, the doodle's vertical movement, and when to end the game.
	 */

	public EventHandler<ActionEvent> getTimeHandler() {
		return new TimeHandler();
	}

	/*
	 * TimeHandler which handles platform generation, platform removal, the doodle's
	 * vertical movement, and when to end the game. Has access to
	 */

	private class TimeHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			this.bounceDood();
			this.scrollLedges();
			this.generateLedges();
		}

		/*
		 * This method updates the _dood's position and velocity and bounces the _dood
		 * if it is intersecting with a platform. Takes no parameters and returns
		 * nothing.
		 */

		private void bounceDood() {
			_dood.updateVel();
			_dood.updateLoc();
			for (int i = 0; i < _ledge.size(); i++) {
				if (_dood.getShapes().intersects(_ledge.get(i).getXPosition(), _ledge.get(i).getYPosition(),
						Constants.PLATFORM_WIDTH, 1) && _dood.getVel() > 0) {
					_dood.setVel(Constants.REBOUND_VELOCITY);
				}
			}
		}

		/*
		 * This method scrolls ledges when the _dood reaches the halfway point up the
		 * screen. When a ledge is removed, it adds 5 to the score. It also checks and
		 * handles what happens when the _dood reaches the bottom of the screen. Takes
		 * no parameters and returns nothing.
		 */

		private void scrollLedges() {
			if (_dood.getYPosition() < Constants.HALF_WAY) {
				double diff = Constants.HALF_WAY - _dood.getYPosition();
				for (int i = 0; i < _ledge.size(); i++) {
					_ledge.get(i).setPosition(_ledge.get(i).getXPosition(), _ledge.get(i).getYPosition() + diff);
					if (_ledge.get(i).getYPosition() > Constants.BOTTOM) {
						_jumpSpace.getChildren().remove(_ledge.get(i).getShapes());
						_score += 5;
						_scoreLabel.setText("Score: " + _score);
						_ledge.remove(0);
					}
				}
				_dood.setPosition(_dood.getXPosition(), Constants.HALF_WAY);
			} else if (_dood.getYPosition() > Constants.BOTTOM - 25) {
				_dood.remove();
				_gameState.setText("GameOver!");
				_gameState.setTextFill(Color.RED);
				_rstBtn = new Button("Restart!");
				_rstBtn.setOnAction(new RestartHandler());
				_rstBtn.setFocusTraversable(false);
				_bottomPane.getChildren().add(_rstBtn);
				_timeline.pause();
			}
		}

		/*
		 * This method generates ledges randomly at the top of the screen as ledges move
		 * down. Takes no parameters and returns nothing.
		 */

		private void generateLedges() {

			if (_ledge.get(_ledge.size() - 1).getYPosition() > Constants.MIN_SPACING) {
				double x = _ledge.get(_ledge.size() - 1).getXPosition()
						+ (Math.pow(-1, (int) (Math.random() * 2)) * (Math.random() * Constants.X_MAX_VARIATION + 40));
				double y = _ledge.get(_ledge.size() - 1).getYPosition()
						- (Math.random() * Constants.Y_MAX_VARIATION + Constants.MIN_SPACING);

				if (x < Constants.LEFT_EDGE) {
					x = _ledge.get(_ledge.size() - 1).getXPosition()
							+ (Math.random() * Constants.X_MAX_VARIATION + Constants.MIN_SPACING);
				} else if (x > Constants.RIGHT_EDGE - 40) {
					x = _ledge.get(_ledge.size() - 1).getXPosition()
							- (Math.random() * Constants.X_MAX_VARIATION + Constants.MIN_SPACING);
				}

				Ledge ledge = new Ledge(_jumpSpace);
				ledge.setPosition(x, y);
				_ledge.add(ledge);
			}
		}
	}

	/*
	 * This class handles the left and right movement of the _dood.
	 */

	private class KeyHandler implements EventHandler<KeyEvent> {

		public KeyHandler() {

		}

		@Override
		public void handle(KeyEvent e) {
			KeyCode keyPressed = e.getCode();
			switch (keyPressed) {
			case LEFT:
				if (_dood.getXPosition() == Constants.LEFT_EDGE) {
					break;
				} else {
					_dood.setPosition(_dood.getXPosition() - 10, _dood.getYPosition());
					break;
				}
			case RIGHT:
				if (_dood.getXPosition() == Constants.RIGHT_EDGE - 20) {
					break;
				} else {
					_dood.setPosition(_dood.getXPosition() + 10, _dood.getYPosition());
					break;
				}
			default:
				break;
			}
			e.consume();
		}

	}

	/*
	 * This handles what happens when someone restarts the game using the restart
	 * button.
	 */

	private class RestartHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			_root.getChildren().remove(_jumpSpace);
			_ledge.clear();
			_organizer.createGamePane();
			Game.this.setInstVar();
			_score = 0;
			_scoreLabel.setText("Score: " + _score);
			_gameState.setText("Jump!");
			_gameState.setTextFill(Color.BLACK);
			_timeline.play();
			_bottomPane.getChildren().remove(_rstBtn);
		}
	}

}
