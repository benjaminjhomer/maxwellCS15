package Pacman;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/*
 * This class implements the interface of Interactable. It has access to
 * getNode(), isType() and interacted(). It is contained within PaneOrganizer
 * and associated with its corresponding Tile.
 */

public class Dot implements Interactable {

	private Ellipse _dot;
	private GridPane _pane;

	public Dot(GridPane pane) {
		_pane = pane;
		_dot = new Ellipse(Constants.DOT_SIZE, Constants.DOT_SIZE);
		_dot.setFill(Color.WHITE);
	}

	// This method has no parameters and returns the Ellipse _dot.

	public Ellipse getNode() {
		return _dot;
	}

	// This method has no parameter and returns the enum Consumable.DOT.

	public Consumable isType() {
		return Consumable.DOT;
	}

	/*
	 * This method has no parameter and returns nothing. It removes _dot from the
	 * parent pane.
	 */

	public void interacted() {
		_pane.getChildren().remove(this.getNode());
	}
}
