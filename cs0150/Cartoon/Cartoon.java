package Cartoon;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/*
 * This is the top-class that creates the contents of each pane. It has access to 
 * createTopPane(), getTopPane(), createEnemyPane(), generateEnemy() and 
 * createSpaceShipPane. It also contains a move handler private class.
 */

public class Cartoon {
	private BorderPane _root;
	private Pane _spaceShipPane;
	private SpaceShip _spaceShip;
	private Pane _topPane;
	private int _score;
	private Pane _enemyPane;
	private Label _loc;

	public Cartoon(BorderPane root) {
		_score = 0;
		_root = root;
	}
	
	/*
	 * Creates a top pane which will have three elements, score and location 
	 * as well as a quit button (is created in PaneOrganizer). This method 
	 * has no parameter and returns nothing.
	 */
	
	public void createTopPane() {
		_topPane = new Pane();
		_topPane.setStyle("-fx-background-color: grey");
		Label score = new Label("Score: " + Integer.toString(_score));
		score.setLayoutX(CONSTANTS.SCORE_X);
		score.setLayoutY(CONSTANTS.SCORE_Y);
		_loc = new Label("ENEMY APPROACHING!");
		_loc.setLayoutX(CONSTANTS.LABEL_LOC_X);
		_loc.setLayoutY(CONSTANTS.LABEL_LOC_Y);
		_loc.setFocusTraversable(false);
		_topPane.getChildren().addAll(score,_loc);
		_root.setTop(_topPane);
		score.setAlignment(Pos.BASELINE_LEFT);
	}

	/* 
	 * Has no parameters and returns the _topPane.
	 */
	
	public Pane getTopPane() {
		return _topPane;
	}
	
	/*
	 * This creates the enemyPane and calls on generateEnemy(). It has no 
	 * parameters and returns nothing.
	 */
	
	public void createEnemyPane() {
		_enemyPane = new Pane();
		this.generateEnemy(_enemyPane);
		_root.setCenter(_enemyPane);
	}
	
	/*
	 * This method takes in a Pane as a parameter and makes 5 instances of Enemy.
	 * These instances will be distributed in 5 locations at the top of the board.
	 */
	
	public void generateEnemy(Pane pane) {
		Enemy e1 = new Enemy(pane,_loc,CONSTANTS.ENEMY_START_X1,CONSTANTS.ENEMY_START_Y);
		Enemy e2 = new Enemy(pane,_loc,CONSTANTS.ENEMY_START_X2,CONSTANTS.ENEMY_START_Y);
		Enemy e3 = new Enemy(pane,_loc,CONSTANTS.ENEMY_START_X3,CONSTANTS.ENEMY_START_Y);
		Enemy e4 = new Enemy(pane,_loc,CONSTANTS.ENEMY_START_X4,CONSTANTS.ENEMY_START_Y);
		Enemy e5 = new Enemy(pane,_loc,CONSTANTS.ENEMY_START_X5,CONSTANTS.ENEMY_START_Y);	
	}
	
	/*
	 * This method has no parameters and creates an the bottom pane which contains 
	 * the space ship. It creates a new instances of MoveHandler and returns nothing.
	 */
	
	public void createSpaceShipPane() {
		_spaceShipPane = new Pane();
		_spaceShipPane.setStyle("-fx-background-color: black");
		_spaceShip = new SpaceShip(_spaceShipPane);
		_root.setBottom(_spaceShipPane);
		_spaceShipPane.addEventHandler(KeyEvent.KEY_PRESSED, new MoveHandler());
		_spaceShipPane.setFocusTraversable(true);
		_spaceShip.setShipLoc(CONSTANTS.SPACESHIP_START);
	}
	
	/*
	 * This MoveHandler listens for key strokes. If the the left or right arrow 
	 * keys are hit then it moves the space ship in the respective direction. If 
	 * the space bar is hit than it creates a new instance of a laser at the current 
	 * position of the space ship.
	 */
	
	private class MoveHandler implements EventHandler<KeyEvent> {
		
		@Override
		public void handle(KeyEvent e) {
		
		KeyCode keyPressed = e.getCode();
		double currentLoc = _spaceShip.getLoc();
			switch (keyPressed) {
		
			case RIGHT:
				if (currentLoc < 225) {
					_spaceShip.setShipLoc(currentLoc+50);
					break;
				}
				break;
			case LEFT:
				if (currentLoc > 75) {
					_spaceShip.setShipLoc(currentLoc-50);
					break;
				}
				break;
			case SPACE:
				new Laser(_enemyPane,_spaceShip.getLoc()+13,315);
			default:
				break;
			}
			
		e.consume();
		}
	}
}
