package Pacman;

import cs015.fnl.PacmanSupport.BoardLocation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/*
 * This class handles all game logic for the game. It has two methods of its own: 
 * setupMap() and setupTimeline(). Both are private. It contains the ghosts and the pacman
 * as well as all the Tiles.
 */

public class GameLogic {

	private GridPane _pane;
	private Tile[][] _mapState;
	private Pacman _pac;
	private Ghost _blinky;
	private Ghost _inky;
	private Ghost _pinky;
	private Ghost _clyde;
	private Timeline _timeline;
	private PaneOrganizer _org;
	private int _score;
	private int _lives;
	private int _chase;
	private int _frightened;
	private int _scatter;
	private GhostPenQ _gPenQ;
	private int _gCounter;

	public GameLogic(GridPane pane, PaneOrganizer org) {
		_pane = pane;
		_pane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
		_org = org;
		_score = 0;
		_clyde = new Ghost(Color.GREEN, 1, 1);
		_pinky = new Ghost(Color.PINK, 1, 0);
		_blinky = new Ghost(Color.RED, 0, 0);
		_inky = new Ghost(Color.LIGHTBLUE, 0, 1);
		_gPenQ = new GhostPenQ();
		_mapState = new Tile[Constants.BOARD_W][Constants.BOARD_H];
		this.setupMap();
		this.setupTimeline();
		_chase = Constants.DUR_CH;
		_scatter = 0;
		_lives = Constants.LIVES;
		_gCounter = Constants.GHOST_PEN_DUR;

	}

	/*
	 * This method has no parameters and returns nothing. It function is to set up
	 * the math graphically and logically (_mapState). It uses getMap() in the cs015
	 * jar which returns a 23X23 array. It places Types of Tile where appropiate as
	 * well as the Pacman and the Ghosts.
	 */

	private void setupMap() {
		for (int j = 0; j < Constants.BOARD_W; j++) {
			for (int i = 0; i < Constants.BOARD_H; i++) {
				switch (cs015.fnl.PacmanSupport.SupportMap.getMap()[i][j]) {
				// Will add a Tile of type wall.
				case WALL:
					Tile wtile = new Tile(BoardLocation.WALL);
					_mapState[j][i] = wtile;
					_pane.add(wtile.getNode(), j, i);
					break;
				// Will add a Tile of type free.
				case FREE:
					Tile ftile = new Tile(BoardLocation.FREE);
					_mapState[j][i] = ftile;
					_pane.add(ftile.getNode(), j, i);
					_clyde.getNode().toFront();
					_pinky.getNode().toFront();
					break;
				/*
				 * Will add a Tile of type free and a dot in that location. Will also add dot to
				 * Tile array.
				 */
				case DOT:
					Tile dtile = new Tile(BoardLocation.FREE);
					_mapState[j][i] = dtile;
					_pane.add(dtile.getNode(), j, i);
					Dot dot = new Dot(_pane);
					dtile.addArray(dot);
					_pane.add(dot.getNode(), j, i);
					GridPane.setHalignment(dot.getNode(), HPos.CENTER);
					break;
				/*
				 * Will add a Tile of type free and an energizer in that location. Will also add
				 * energizer to Tile array.
				 */
				case ENERGIZER:
					Tile etile = new Tile(BoardLocation.FREE);
					_mapState[j][i] = etile;
					_pane.add(etile.getNode(), j, i);
					Energizer e = new Energizer(_pane);
					etile.addArray(e);
					_pane.add(e.getNode(), j, i);
					GridPane.setHalignment(e.getNode(), HPos.CENTER);
					break;
				// Will add a Tile of type free and _pac in that location.
				case PACMAN_START_LOCATION:
					Tile ptile = new Tile(BoardLocation.FREE);
					_mapState[j][i] = ptile;
					_pane.add(ptile.getNode(), j, i);
					_pac = new Pacman();
					_pane.add(_pac.getNode(), j, i);
					GridPane.setHalignment(_pac.getNode(), HPos.CENTER);
					break;
				/*
				 * Will add a Tile of type free and ghost instances to their respective place
				 * relative to this Tile.
				 */
				case GHOST_START_LOCATION:
					Tile gtile = new Tile(BoardLocation.FREE);
					_mapState[j][i] = gtile;
					_pane.add(gtile.getNode(), j, i);

					_pane.add(_blinky.getNode(), j, i - 2);
					GridPane.setHalignment(_blinky.getNode(), HPos.CENTER);

					_pane.add(_pinky.getNode(), j, i);
					GridPane.setHalignment(_pinky.getNode(), HPos.CENTER);
					_gPenQ.enqueue(_pinky);

					_pane.add(_inky.getNode(), j - 1, i);
					GridPane.setHalignment(_inky.getNode(), HPos.CENTER);
					_gPenQ.enqueue(_inky);

					_pane.add(_clyde.getNode(), j + 1, i);
					GridPane.setHalignment(_clyde.getNode(), HPos.CENTER);
					_gPenQ.enqueue(_clyde);
					break;
				default:
					break;
				}
			}
		}
	}

	/*
	 * This method has no parameters and and returns nothing. It creates a new
	 * Timeline which controls all of the logic of the game that's needed at every
	 * time step.
	 */

	private void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}

	/*
	 * This class is private and controls all logic that is needed at every time
	 * step. It has eleven methods: handle(), checkWin(), ghostPen(), modeGhost(),
	 * move(), checkValidity(), checkTileArray(), ghostCaught(), pacCaught(),
	 * winner(), and gameOver(). handle() is called every .5 seconds.
	 */

	private class TimeHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			this.move(_pac.getNode(), _pac.getCurrent());
			this.checkTileArray();
			this.modeGhost(_blinky, Color.RED, 1, 1);
			this.modeGhost(_inky, Color.LIGHTBLUE, 21, 21);
			this.modeGhost(_pinky, Color.PINK, 21, 1);
			this.modeGhost(_clyde, Color.GREEN, 1, 21);
			this.checkTileArray();
			this.ghostPen();
			this.checkWin();
		}

		/*
		 * This method has no parameters and returns nothing. It checks for the win
		 * condition and calls the winner() method.
		 */

		private void checkWin() {
			Boolean win = true;
			for (int j = 0; j < Constants.BOARD_H; j++) {
				for (int i = 0; i < Constants.BOARD_W; i++) {
					if (_mapState[i][j].checkList(false)[1] == true || _mapState[i][j].checkList(false)[0] == true) {
						win = false;
						break;
					}
				}
			}

			if (win == true) {
				this.winner();
			}
		}

		/*
		 * This method has no parameters and returns nothing. It controls the release of
		 * the ghost from the ghost pen.
		 */

		private void ghostPen() {
			if (!_gPenQ.isEmpty()) {
				if (_gCounter == 0) {
					Ghost gh = _gPenQ.dequeue();
					_pane.getChildren().remove(gh.getNode());
					_pane.add(gh.getNode(), Constants.PEN_START_X, Constants.PEN_START_Y);
					_gCounter = Constants.GHOST_PEN_DUR;
				}
				_gCounter--;
			}
		}

		/*
		 * This method has 4 parameters: a Ghost, a Color, and two integers. The ghost
		 * is the ghost that needs to be moved. The Color is the normal color of said
		 * ghost. The integers are the target x and y location in the GridPane. This
		 * method will move the ghost depending on what mode this is in.
		 */

		private void modeGhost(Ghost gh, Color color, int tx, int ty) {
			if (_frightened > 0) { // Will moves the ghost to a random X and random Y ever move
				int x = (int) (Math.random() * 22);
				int y = (int) (Math.random() * 22);
				_mapState[GridPane.getColumnIndex(gh.getNode())][GridPane.getRowIndex(gh.getNode())].removeArray(gh);
				this.move(gh.getNode(), this.moveGhosts(gh, x, y));
				_mapState[GridPane.getColumnIndex(gh.getNode())][GridPane.getRowIndex(gh.getNode())].addArray(gh);
				this.checkTileArray();
				gh.setColor(Color.GREY);
				_frightened--;
			} else if (_chase > 0) { // Will move the ghost towards the _pacman's location plus its specific offset.
				_mapState[GridPane.getColumnIndex(gh.getNode())][GridPane.getRowIndex(gh.getNode())].removeArray(gh);
				this.move(gh.getNode(), this.moveGhosts(gh, GridPane.getColumnIndex(_pac.getNode()) + gh.getOffX(),
						GridPane.getRowIndex(_pac.getNode()) + gh.getOffY()));
				_mapState[GridPane.getColumnIndex(gh.getNode())][GridPane.getRowIndex(gh.getNode())].addArray(gh);
				this.checkTileArray();
				gh.setColor(color);
				_chase--;
				if (_chase == 0) { // starts scatter phase when chase phase ends.
					_scatter = Constants.DUR_S;
				}
			} else if (_scatter > 0) { // Will move the ghost towards the target x and y
				_mapState[GridPane.getColumnIndex(gh.getNode())][GridPane.getRowIndex(gh.getNode())].removeArray(gh);
				this.move(gh.getNode(), this.moveGhosts(gh, tx, ty));
				_mapState[GridPane.getColumnIndex(gh.getNode())][GridPane.getRowIndex(gh.getNode())].addArray(gh);
				this.checkTileArray();
				gh.setColor(color);
				_scatter--;
				if (_scatter == 0) { // starts chase phase when scatter phase ends.
					_chase = Constants.DUR_CH;
				}
			}
		}

		/*
		 * This method returns nothing and has two parameters, a Node and a Direction.
		 * It will move the node one square in the direction specified and will wrap the
		 * object if it moves out of bounds.
		 */

		private void move(Node n, Direction d) {
			switch (d) {
			case LEFT:
				if (GridPane.getColumnIndex(n) - 1 < 0) {
					_pane.getChildren().remove(n);
					_pane.add(n, Constants.BOARD_W - 1, (int) (Constants.BOARD_W / 2));
				} else if (this.checkValidity(GridPane.getColumnIndex(n) - 1, GridPane.getRowIndex(n))) {
					_pane.getChildren().remove(n);
					_pane.add(n, GridPane.getColumnIndex(n) - 1, GridPane.getRowIndex(n));
				}
				break;
			case RIGHT:
				if (GridPane.getColumnIndex(n) + 1 >= Constants.BOARD_W) {
					_pane.getChildren().remove(n);
					_pane.add(n, 0, (int) (Constants.BOARD_H / 2));
				} else if (this.checkValidity(GridPane.getColumnIndex(n) + 1, GridPane.getRowIndex(n))) {
					_pane.getChildren().remove(n);
					_pane.add(n, GridPane.getColumnIndex(n) + 1, GridPane.getRowIndex(n));
				}
				break;
			case UP:
				if (this.checkValidity(GridPane.getColumnIndex(n), GridPane.getRowIndex(n) - 1)) {
					_pane.getChildren().remove(n);
					_pane.add(n, GridPane.getColumnIndex(n), GridPane.getRowIndex(n) - 1);
				}
				break;
			case DOWN:
				if (this.checkValidity(GridPane.getColumnIndex(n), GridPane.getRowIndex(n) + 1)) {
					_pane.getChildren().remove(n);
					_pane.add(n, GridPane.getColumnIndex(n), GridPane.getRowIndex(n) + 1);
				}
				break;
			default:
				break;
			}
		}

		/*
		 * This method has two integer parameters (which are target indices in
		 * _mapState). It returns a boolean which is true if the target indice is not of
		 * Wall type.
		 */

		private boolean checkValidity(int x, int y) {
			if (_mapState[x][y].getType() != BoardLocation.WALL) {
				return true;
			} else {
				return false;
			}
		}

		/*
		 * This method has no parameters and returns nothing. It checks the Tile that
		 * the _pac certainly sits on to see if its array has a dot, an Energizer, or a
		 * Ghost. Based on whether is does, it complete the respective action.
		 */

		private void checkTileArray() {
			boolean[] containedTile = _mapState[GridPane.getColumnIndex(_pac.getNode())][GridPane
					.getRowIndex(_pac.getNode())].checkList(true);
			if (containedTile[0]) {
				_score += 10;
				_org.setScoreDis(_score);
			}
			if (containedTile[1]) {
				_score += 100;
				_org.setScoreDis(_score);
				_frightened = Constants.DUR_F;
			}
			if (containedTile[2]) {
				if (_frightened > 0) {
					this.ghostCaught();
				} else {
					this.pacCaught();
				}
			}
		}

		/*
		 * This method has no parameters and returns nothing. It handles what happens
		 * when the Ghost is caught when in Frightened Mode.
		 */

		private void ghostCaught() {
			_score += 200;
			_org.setScoreDis(_score);
			int x = GridPane.getColumnIndex(_pac.getNode());
			int y = GridPane.getRowIndex(_pac.getNode());
			// checks to see which ghost has been caught by _pac.
			if (x == GridPane.getColumnIndex(_blinky.getNode()) && y == GridPane.getRowIndex(_blinky.getNode())) {
				_mapState[x][y].removeArray(_blinky);
				_pane.getChildren().remove(_blinky.getNode());
				_pane.add(_blinky.getNode(), Constants.P_START_X, Constants.P_START_Y);
				_gPenQ.enqueue(_blinky);
			} else if (x == GridPane.getColumnIndex(_inky.getNode()) && y == GridPane.getRowIndex(_inky.getNode())) {
				_mapState[x][y].removeArray(_inky);
				_pane.getChildren().remove(_inky.getNode());
				_pane.add(_inky.getNode(), Constants.P_START_X, Constants.P_START_Y);
				_gPenQ.enqueue(_inky);
			} else if (x == GridPane.getColumnIndex(_pinky.getNode()) && y == GridPane.getRowIndex(_pinky.getNode())) {
				_mapState[x][y].removeArray(_pinky);
				_pane.getChildren().remove(_pinky.getNode());
				_pane.add(_pinky.getNode(), Constants.P_START_X, Constants.P_START_Y);
				_gPenQ.enqueue(_pinky);
			} else if (x == GridPane.getColumnIndex(_clyde.getNode()) && y == GridPane.getRowIndex(_clyde.getNode())) {
				_mapState[x][y].removeArray(_clyde);
				_pane.getChildren().remove(_clyde.getNode());
				_pane.add(_clyde.getNode(), Constants.P_START_X, Constants.P_START_Y);
				_gPenQ.enqueue(_clyde);
			}
		}

		/*
		 * This method has no parameters and returns nothing. It handles what happens
		 * when the _pac is caught by the ghost not in Frightened Mode.
		 */

		private void pacCaught() {
			_pane.getChildren().remove(_pac.getNode());
			_pane.add(_pac.getNode(), Constants.PAC_START_X, Constants.PAC_START_Y);
			// removes Ghosts to their starting position and starts new GhostPenQ
			_gPenQ = new GhostPenQ();
			
			_mapState[GridPane.getColumnIndex(_blinky.getNode())][GridPane.getRowIndex(_blinky.getNode())]
					.removeArray(_blinky);
			_pane.getChildren().remove(_blinky.getNode());
			_pane.add(_blinky.getNode(), Constants.PEN_START_X, Constants.PEN_START_Y);

			_mapState[GridPane.getColumnIndex(_inky.getNode())][GridPane.getRowIndex(_inky.getNode())]
					.removeArray(_inky);
			_pane.getChildren().remove(_inky.getNode());
			_pane.add(_inky.getNode(), Constants.I_START_X, Constants.I_START_Y);
			_gPenQ.enqueue(_inky);

			_mapState[GridPane.getColumnIndex(_pinky.getNode())][GridPane.getRowIndex(_pinky.getNode())]
					.removeArray(_pinky);
			_pane.getChildren().remove(_pinky.getNode());
			_pane.add(_pinky.getNode(), Constants.P_START_X, Constants.P_START_Y);
			_gPenQ.enqueue(_pinky);

			_mapState[GridPane.getColumnIndex(_clyde.getNode())][GridPane.getRowIndex(_clyde.getNode())]
					.removeArray(_clyde);
			_pane.getChildren().remove(_clyde.getNode());
			_pane.add(_clyde.getNode(), Constants.C_START_X, Constants.C_START_Y);
			_gPenQ.enqueue(_clyde);

			_pac.setDirection(Direction.UP);
			_lives--;
			_org.setLives(_lives);
			if (_lives == 0) {
				this.gameOver();
			}
		}

		/*
		 * This method has no parameters and returns nothing. It handles what happens
		 * when the win condition is satisfied.
		 */

		private void winner() {
			_timeline.pause();
			Label lbl = new Label("YOU WON!");
			lbl.setTextFill(Color.RED);
			lbl.setStyle("-fx-font-size: 50");
			_org.getRoot().setCenter(lbl);
		}

		/*
		 * This method has no parameters and returns nothing. It handles what happens
		 * when the gameOver condition is satisfied.
		 */

		private void gameOver() {
			_timeline.pause();
			Label lbl = new Label("GAME OVER!");
			lbl.setTextFill(Color.RED);
			lbl.setStyle("-fx-font-size: 50");
			_org.getRoot().setCenter(lbl);
		}

		/*
		 * This method has 3 parameters and returns a Direction enum. It takes in a
		 * ghost, and two target integers. It will find the direction the Ghost should
		 * go in order to get to the target x and y without doing a 180.
		 */

		private Direction moveGhosts(Ghost g, int x, int y) {
			Direction[][] direction = new Direction[Constants.BOARD_W][Constants.BOARD_H];
			BFSQueue queue = new BFSQueue();
			Tile current = _mapState[GridPane.getColumnIndex(g.getNode())][GridPane.getRowIndex(g.getNode())];
			int xLoc = GridPane.getColumnIndex(g.getNode());
			int yLoc = GridPane.getRowIndex(g.getNode());
			int xRLoc = xLoc + 1;
			int xLLoc = xLoc - 1;
			// Makes sure there won't be an out of bounds error.
			if (xLLoc < 0) {
				xLLoc = Constants.BOARD_W - 1;
			}
			if (xRLoc == Constants.BOARD_W) {
				xRLoc = 0;
			}
			double d = 100;
			Tile shortest = current;
			direction[xLoc][yLoc] = Direction.NONE;
			// Adds initial set of squares, keeping in mind not to do a complete 180.
			if (g.getDirection() != Direction.LEFT && _mapState[xRLoc][yLoc].getType() != BoardLocation.WALL) {
				queue.enqueue(_mapState[xRLoc][yLoc]);
				direction[xRLoc][yLoc] = Direction.RIGHT;
			}
			if (g.getDirection() != Direction.RIGHT && _mapState[xLLoc][yLoc].getType() != BoardLocation.WALL) {
				queue.enqueue(_mapState[xLLoc][yLoc]);
				direction[xLLoc][yLoc] = Direction.LEFT;
			}
			if (g.getDirection() != Direction.DOWN && _mapState[xLoc][yLoc - 1].getType() != BoardLocation.WALL) {
				queue.enqueue(_mapState[xLoc][yLoc - 1]);
				direction[xLoc][yLoc - 1] = Direction.UP;
			}
			if (g.getDirection() != Direction.UP && _mapState[xLoc][yLoc + 1].getType() != BoardLocation.WALL) {
				queue.enqueue(_mapState[xLoc][yLoc + 1]);
				direction[xLoc][yLoc + 1] = Direction.DOWN;
			}
			// Adds squares based on next in line in the queue.
			while (!queue.isEmpty()) {
				current = queue.dequeue();
				xLoc = GridPane.getColumnIndex(current.getNode());
				yLoc = GridPane.getRowIndex(current.getNode());
				xRLoc = xLoc + 1;
				xLLoc = xLoc - 1;
				// Returns direction if it finds it's target Tile.
				if (xLoc == x && yLoc == y) {
					g.setDirection(direction[x][y]);
					return direction[x][y];
				}
				if (Math.sqrt(Math.pow(xLoc - x, 2) + Math.pow(yLoc - y, 2)) <= d) {
					shortest = current;
				}
				if (xLLoc < 0) {
					xLLoc = Constants.BOARD_W - 1;
				}
				if (xRLoc == Constants.BOARD_W) {
					xRLoc = 0;
				}
				if (_mapState[xRLoc][yLoc].getType() != BoardLocation.WALL && direction[xRLoc][yLoc] == null) {
					queue.enqueue(_mapState[xRLoc][yLoc]);
					direction[xRLoc][yLoc] = direction[xLoc][yLoc];
				}
				if (_mapState[xLLoc][yLoc].getType() != BoardLocation.WALL && direction[xLLoc][yLoc] == null) {
					queue.enqueue(_mapState[xLLoc][yLoc]);
					direction[xLLoc][yLoc] = direction[xLoc][yLoc];
				}
				if (_mapState[xLoc][yLoc - 1].getType() != BoardLocation.WALL && direction[xLoc][yLoc - 1] == null) {
					queue.enqueue(_mapState[xLoc][yLoc - 1]);
					direction[xLoc][yLoc - 1] = direction[xLoc][yLoc];
				}
				if (_mapState[xLoc][yLoc + 1].getType() != BoardLocation.WALL && direction[xLoc][yLoc + 1] == null) {
					queue.enqueue(_mapState[xLoc][yLoc + 1]);
					direction[xLoc][yLoc + 1] = direction[xLoc][yLoc];
				}
			}
			// Returns the Direction to the closest square to the target.
			g.setDirection(
					direction[GridPane.getColumnIndex(shortest.getNode())][GridPane.getRowIndex(shortest.getNode())]);
			return direction[GridPane.getColumnIndex(shortest.getNode())][GridPane.getRowIndex(shortest.getNode())];
		}
	}

	/*
	 * This class handles all key inputs that control the pacman's movement.
	 */

	private class KeyHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent e) {
			KeyCode key = e.getCode();

			switch (key) {
			case LEFT:
				_pac.setDirection(Direction.LEFT);
				break;
			case RIGHT:
				_pac.setDirection(Direction.RIGHT);
				break;
			case UP:
				_pac.setDirection(Direction.UP);
				break;
			case DOWN:
				_pac.setDirection(Direction.DOWN);
				break;
			default:
				break;
			}
			e.consume();

		}

	}
}
