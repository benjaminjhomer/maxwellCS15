package Pacman;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/*
 * This class implements the interface of Interactable. It has access to
 * getNode(), isType() and interacted(). It is contained within PaneOrganizer
 * and associated with its corresponding Tile.
 */

public class Energizer implements Interactable {

	private Ellipse _energizer;
	private GridPane _pane;

	public Energizer(GridPane pane) {
		_pane = pane;
		_energizer = new Ellipse(Constants.E_SIZE, Constants.E_SIZE);
		_energizer.setFill(Color.WHITE);
	}

	// This method has no parameters and returns the Ellipse _dot.

	public Ellipse getNode() {
		return _energizer;
	}

	// This method has no parameter and returns the enum Consumable.ENERGIZER.

	public Consumable isType() {
		return Consumable.ENERGIZER;
	}

	/*
	 * This method has no parameter and returns nothing. It removes _energizer from
	 * the parent pane.
	 */

	public void interacted() {
		_pane.getChildren().remove(this.getNode());
	}
}
