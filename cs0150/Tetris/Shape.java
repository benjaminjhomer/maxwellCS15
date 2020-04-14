package Tetris;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * This class makes 4 Blocks into 7 random shapes. Its methods are:
 * checkValidity, moveDown, moveSidewards, rotate, and addToBlockPos.
 */

public class Shape {

	private Rectangle[][] _posArray;
	private Block _b1;
	private Block _b2;
	private Block _b3;
	private Block _b4;

	public Shape(Rectangle[][] posArray, GridPane pane) {
		_posArray = posArray;
		switch ((int) (Math.random() * 7)) {
		case 0:
			_b1 = new Block(pane, Constants.BOARDW / 2 - 2, 0, Color.RED);
			_b2 = new Block(pane, Constants.BOARDW / 2 - 1, 0, Color.RED);
			_b3 = new Block(pane, Constants.BOARDW / 2, 0, Color.RED);
			_b4 = new Block(pane, Constants.BOARDW / 2 + 1, 0, Color.RED);
			break;
		case 1:
			_b1 = new Block(pane, Constants.BOARDW / 2 - 1, 0, Color.DARKBLUE);
			_b2 = new Block(pane, Constants.BOARDW / 2, 0, Color.DARKBLUE);
			_b3 = new Block(pane, Constants.BOARDW / 2 + 1, 0, Color.DARKBLUE);
			_b4 = new Block(pane, Constants.BOARDW / 2, 1, Color.DARKBLUE);
			break;
		case 2:
			_b1 = new Block(pane, Constants.BOARDW / 2 - 1, 0, Color.YELLOW);
			_b2 = new Block(pane, Constants.BOARDW / 2 - 1, 1, Color.YELLOW);
			_b3 = new Block(pane, Constants.BOARDW / 2 - 1, 2, Color.YELLOW);
			_b4 = new Block(pane, Constants.BOARDW / 2, 2, Color.YELLOW);
			break;
		case 3:
			_b1 = new Block(pane, Constants.BOARDW / 2 - 1, 0, Color.PURPLE);
			_b2 = new Block(pane, Constants.BOARDW / 2 - 1, 1, Color.PURPLE);
			_b3 = new Block(pane, Constants.BOARDW / 2 - 1, 2, Color.PURPLE);
			_b4 = new Block(pane, Constants.BOARDW / 2 - 2, 2, Color.PURPLE);
			break;
		case 4:
			_b1 = new Block(pane, Constants.BOARDW / 2 - 1, 0, Color.GREEN);
			_b2 = new Block(pane, Constants.BOARDW / 2, 0, Color.GREEN);
			_b3 = new Block(pane, Constants.BOARDW / 2 - 1, 1, Color.GREEN);
			_b4 = new Block(pane, Constants.BOARDW / 2, 1, Color.GREEN);
			break;
		case 5:
			_b1 = new Block(pane, Constants.BOARDW / 2 - 1, 0, Color.ORANGE);
			_b2 = new Block(pane, Constants.BOARDW / 2 - 1, 1, Color.ORANGE);
			_b3 = new Block(pane, Constants.BOARDW / 2, 1, Color.ORANGE);
			_b4 = new Block(pane, Constants.BOARDW / 2, 2, Color.ORANGE);
			break;
		case 6:
			_b1 = new Block(pane, Constants.BOARDW / 2, 0, Color.HOTPINK);
			_b2 = new Block(pane, Constants.BOARDW / 2, 1, Color.HOTPINK);
			_b3 = new Block(pane, Constants.BOARDW / 2 - 1, 1, Color.HOTPINK);
			_b4 = new Block(pane, Constants.BOARDW / 2 - 1, 2, Color.HOTPINK);
			break;
		}
	}

	/*
	 * This method can check move validity in the X and Y directions. It has two
	 * parameters, both of which are integers. It returns a boolean, true if the
	 * move is valid and false if it isn't
	 */

	public boolean checkValidity(int x, int y) {
		if (_posArray[_b1.getXIndex() + 1 + x][_b1.getYIndex() + y] != null
				|| _posArray[_b2.getXIndex() + 1 + x][_b2.getYIndex() + y] != null
				|| _posArray[_b3.getXIndex() + 1 + x][_b3.getYIndex() + y] != null
				|| _posArray[_b4.getXIndex() + 1 + x][_b4.getYIndex() + y] != null) {
			return false;
		} else {
			return true;
		}
	}

	// Moves the shape down. Has no parameters or outputs.

	public void moveDown() {
		GridPane.setRowIndex(_b1.getNode(), _b1.getYIndex() + 1);
		GridPane.setRowIndex(_b2.getNode(), _b2.getYIndex() + 1);
		GridPane.setRowIndex(_b3.getNode(), _b3.getYIndex() + 1);
		GridPane.setRowIndex(_b4.getNode(), _b4.getYIndex() + 1);
	}

	// Moves the shape sidewards. Has an int as a parameter and no outputs.

	public void moveSidewards(int x) {
		GridPane.setColumnIndex(_b1.getNode(), _b1.getXIndex() + x);
		GridPane.setColumnIndex(_b2.getNode(), _b2.getXIndex() + x);
		GridPane.setColumnIndex(_b3.getNode(), _b3.getXIndex() + x);
		GridPane.setColumnIndex(_b4.getNode(), _b4.getXIndex() + x);
	}

	// Checks validity then rotates the shape. Has no parameters or outputs.

	public void rotate() {
		int x1 = _b3.getXIndex() - _b3.getYIndex() + _b1.getYIndex();
		int x2 = _b3.getXIndex() - _b3.getYIndex() + _b2.getYIndex();
		int x3 = _b3.getXIndex() - _b3.getYIndex() + _b3.getYIndex();
		int x4 = _b3.getXIndex() - _b3.getYIndex() + _b4.getYIndex();
		int y1 = _b3.getYIndex() + _b3.getXIndex() - _b1.getXIndex();
		int y2 = _b3.getYIndex() + _b3.getXIndex() - _b2.getXIndex();
		int y3 = _b3.getYIndex() + _b3.getXIndex() - _b3.getXIndex();
		int y4 = _b3.getYIndex() + _b3.getXIndex() - _b4.getXIndex();
		if (!(x1 < 0 || x2 < 0 || x3 < 0 || x4 < 0 || x1 > Constants.BOARDW + 1 || x2 > Constants.BOARDW + 1
				|| x3 > Constants.BOARDW + 1 || x4 > Constants.BOARDW + 1 || y1 < 0 || y2 < 0 || y3 < 0 || y4 < 0
				|| y1 >= Constants.BOARDH || y2 > Constants.BOARDH || y3 > Constants.BOARDH || y4 > Constants.BOARDH)) {
			if (_posArray[x1 + 1][y1] == null && _posArray[x2 + 1][y2] == null && _posArray[x3 + 1][y3] == null
					&& _posArray[x4 + 1][y4] == null) {
				GridPane.setColumnIndex(_b1.getNode(), x1);
				GridPane.setColumnIndex(_b2.getNode(), x2);
				GridPane.setColumnIndex(_b3.getNode(), x3);
				GridPane.setColumnIndex(_b4.getNode(), x4);
				GridPane.setRowIndex(_b1.getNode(), y1);
				GridPane.setRowIndex(_b2.getNode(), y2);
				GridPane.setRowIndex(_b3.getNode(), y3);
				GridPane.setRowIndex(_b4.getNode(), y4);
			}
		}
	}

	// Adds to logical array. No parameters or outputs.

	public void addToBlockPos() {
		_posArray[_b1.getXIndex() + 1][_b1.getYIndex()] = _b1.getNode();
		_posArray[_b2.getXIndex() + 1][_b2.getYIndex()] = _b2.getNode();
		_posArray[_b3.getXIndex() + 1][_b3.getYIndex()] = _b3.getNode();
		_posArray[_b4.getXIndex() + 1][_b4.getYIndex()] = _b4.getNode();
	}

}
