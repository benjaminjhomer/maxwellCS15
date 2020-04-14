package Tetris;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Wrapper for the _rect. Has three getters, getNode, getXIndex, and getYIndex.
 */

public class Block {

	private Rectangle _rect;

	public Block(GridPane pane, int x, int y, Color color) {
		_rect = new Rectangle(Constants.BLOCK_SIDE_L, Constants.BLOCK_SIDE_L);
		_rect.setFill(color);
		_rect.setStrokeWidth(Constants.BLOCKSTROKE);
		_rect.setArcHeight(Constants.BLOCKARC);
		_rect.setArcWidth(Constants.BLOCKARC);
		_rect.setStroke(Color.BLACK);
		pane.add(_rect, x, y);
	}

	// Getter for the instance variable _rect.

	public Rectangle getNode() {
		return _rect;
	}

	// Getter for the Column the _rect is in.

	public int getXIndex() {
		return GridPane.getColumnIndex(_rect);
	}

	// Getter for the row the _rect is in.

	public int getYIndex() {
		return GridPane.getRowIndex(_rect);
	}
}
