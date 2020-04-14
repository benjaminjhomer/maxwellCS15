package Tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/*
 * This class handles all logic for the game. Its methods are: setupTimeline,
 * moveShapeDown, clearRows, and getScore. It has two inner classes, TimHandler
 * and KeyHandler.
 */

public class GameLogic {

	private Timeline _timeline;
	private Shape _shape;
	private GridPane _gamePane;
	private Rectangle[][] _blockPos;
	private boolean _gameRunning;
	private int _score;
	private PaneOrganizer _org;
	
	public GameLogic(GridPane pane, PaneOrganizer org) {
		_org = org;
		_gamePane = pane;
		_gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
		_gamePane.setFocusTraversable(true);
		_score = 0;
		_blockPos = new Rectangle[Constants.BOARDW + 2][Constants.BOARDH + 1];
		for (int i = 0; i < Constants.BOARDH + 1; i++) {
			_blockPos[0][i] = new Rectangle();
			_blockPos[Constants.BOARDW + 1][i] = new Rectangle();
		}
		for (int i = 0; i < Constants.BOARDW + 2; i++) {
			_blockPos[i][Constants.BOARDH] = new Rectangle();
		}
		_gameRunning = true;

	}

	// This method starts the Timeline. It has no parameters and returns nothing.

	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}

	/*
	 * This method has no parameter and returns nothing. It either moves the shape
	 * down after checking validity, or spawns a new shape.
	 */

	private void moveShapeDown() {
		if (!_shape.checkValidity(0, 1)) {
			_shape.addToBlockPos();
			_shape = new Shape(_blockPos, _gamePane);
			_score += 2;
			_org.setScore();
		} else {
			_shape.moveDown();
		}
	}

	/*
	 * This method checks to see if rows need to be cleared at every timestep. It
	 * has no parameters and returns nothing.
	 */

	private void clearRows() {

		for (int j = 0; j < Constants.BOARDH; j++) {
			boolean rowFilled = true;
			for (int i = 1; i < Constants.BOARDW + 1; i++) {
				if (_blockPos[i][j] == null) {
					rowFilled = false;
					break;
				}
			}
			if (rowFilled) {
				for (int i = 1; i < Constants.BOARDW + 1; i++) {
					_gamePane.getChildren().remove(_blockPos[i][j]);
					_blockPos[i][j] = null;
				}
				for (int jp = j - 1; jp >= 0; jp--) {
					for (int i = 1; i < Constants.BOARDW + 1; i++) {
						if (_blockPos[i][jp] != null) {
							GridPane.setRowIndex(_blockPos[i][jp], jp + 1);
							_blockPos[i][jp + 1] = _blockPos[i][jp];
							_blockPos[i][jp] = null;
							_score++;
							_org.setScore();
						}
					}
				}
			}
		}
	}

	// This method is a getter for the instance variable _score.

	public int getScore() {
		return _score;
	}

	/*
	 * This class handles making sure the method to clear rows is called and that
	 * the shapes move down the screen.
	 */

	private class TimeHandler implements EventHandler<ActionEvent> {

		public TimeHandler() {
			_shape = new Shape(_blockPos, _gamePane);
		}

		public void handle(ActionEvent e) {
			this.gameOver();
			if (_gameRunning) {

				GameLogic.this.moveShapeDown();
				GameLogic.this.clearRows();
			}
		}

		/*
		 * This method handles checking if the game is over and what to do if it is. It
		 * has no parameters and outputs.
		 */

		private void gameOver() {
			boolean rowFilled = false;
			for (int i = 1; i < Constants.BOARDW + 1; i++) {
				if (!(_blockPos[i][0] == null)) {
					rowFilled = true;
					i = Constants.BOARDW + 1;
				}
			}
			if (rowFilled) {
				_timeline.stop();
				Label gameOver = new Label("Game Over! You suck!");
				gameOver.setTextFill(Color.RED);
				gameOver.setFont(new Font("Helvetica", 50));
				_org.addGameOver(gameOver);
				_gameRunning = false;
			}
		}
	}

	/*
	 * This class handles all key inputs. It also checks to see if the game is
	 * paused or not.
	 */

	private class KeyHandler implements EventHandler<KeyEvent> {

		public void handle(KeyEvent e) {
			KeyCode event = e.getCode();
			if (_gameRunning) {
				switch (event) {
				case LEFT:
					if (_shape.checkValidity(-1, 0)) {
						_shape.moveSidewards(-1);
					}
					break;
				case RIGHT:
					if (_shape.checkValidity(1, 0)) {
						_shape.moveSidewards(1);
					}
					break;
				case DOWN:
					if (_shape.checkValidity(0, 1)) {
						GameLogic.this.moveShapeDown();
						GameLogic.this.clearRows();
					}
					break;
				case UP:
					_shape.rotate();
					break;
				case SPACE:
					while (_shape.checkValidity(0, 1)) {
						GameLogic.this.moveShapeDown();
					}
					GameLogic.this.moveShapeDown();
					GameLogic.this.clearRows();
					break;
				case P:
					_timeline.pause();
					_gameRunning = false;
					_org.pauseLabel(true);
					break;
				default:
					break;
				}
			} else if (event == KeyCode.P) {
				_timeline.play();
				_gameRunning = true;
				_org.pauseLabel(false);
			}
			e.consume();
		}

	}
}
