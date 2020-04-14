package lab5;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 ____                      _              _ _ _     _
|  _ \  ___    _ __   ___ | |_    ___  __| (_) |_  | |
| | | |/ _ \  | '_ \ / _ \| __|  / _ \/ _` | | __| | |
| |_| | (_) | | | | | (_) | |_  |  __/ (_| | | |_  |_|
|____/ \___/  |_| |_|\___/ \__|  \___|\__,_|_|\__| (_)

This is support code for the lab. You may look at it if you are interested,
but you will not have to change anything to complete your lab.
*/

/*
 * This class creates a visualizer for ArrayBuilder to display the different Arrays
 */
public class ArrayVisualizer {

	private Tab _tab;
	private Pane _contentPane;

	public ArrayVisualizer() {
		_tab = new Tab("Arrays");
		_tab.setContent(this.createContent());
	}

	/*
	 * This creates the root pane and adds the control Pane and content
	 * Pane to it.
	 */
	private Node createContent() {
		BorderPane pane = new BorderPane();
		pane.setPrefSize(Constants.PANEL_W, Constants.PANEL_H + Constants.CP_HEIGHT);
		pane.setBottom(this.createControlPane());
		_contentPane = new Pane();
		pane.setTop(_contentPane);
		return pane;
	}

	/*
	 * This creates the control Pane with the buttons to control the
	 * displayed squares
	 */
	private Pane createControlPane() {
		HBox controlPane = new HBox(5);
		controlPane.setAlignment(Pos.CENTER);
		controlPane.setPadding(new Insets(5, 5, 5, 5));
		Button stripesButton = new Button("Stripes");
		Button checkerboardButton = new Button("Checkerboard");
		Button halfHalfButton = new Button("Half and Half");
		Button diagonalButton = new Button("Diagonal");
		stripesButton.setOnMouseReleased(new ButtonListener(ArrayType.STRIPE));
		checkerboardButton.setOnMouseReleased(new ButtonListener(ArrayType.CHECKERBOARD));
		halfHalfButton.setOnMouseReleased(new ButtonListener(ArrayType.HALF_HALF));
		diagonalButton.setOnMouseReleased(new ButtonListener(ArrayType.DIAGONAL));
		controlPane.getChildren().addAll(stripesButton, checkerboardButton, halfHalfButton, diagonalButton);
		return controlPane;
	}

	/*
	 * This method returns the ArrayVisualizer tab.
	 */
	public Tab toTab() {
		return _tab;
	}

	/*
	 * Given an ArrayList of colors, this method generates an Array of 
	 * Rectangles with colors based on the passed in Array of colors.
	 * This method is used in the ArrayBuilder class
	 */
	public static Rectangle[][] generateSquares(Color[][] colors) {
		Rectangle[][] squares;
		if (colors != null) {
			squares = new Rectangle[colors.length][colors[0].length];
			int maxSquareWidth = Constants.PANEL_W / colors[0].length - Constants.BORDER * 2;
			int maxSquareHeight = Constants.PANEL_H / colors.length - Constants.BORDER * 2;
			int squareSide = Math.min(maxSquareWidth, maxSquareHeight);
			for (int i = 0; i < colors.length; i++) {
				for (int j = 0; j < colors[1].length; j++) {
					Rectangle newSquare = new Rectangle(squareSide, squareSide, colors[i][j]);
					newSquare.setStrokeWidth(Constants.BORDER);
					newSquare.setStroke(Color.BLACK);
					int squareX = j * (squareSide + 1) + (Constants.PANEL_W - colors[0].length * (squareSide + 1)) / 2;
					int squareY = i * (squareSide + 1) + (Constants.PANEL_H - colors.length * (squareSide + 1)) / 2;
					newSquare.setX(squareX);
					newSquare.setY(squareY);
					squares[i][j] = newSquare;
				}
			}
			return squares;
		}
		return null;
	}

	/**
	 * Listener that calls appropriate method of student's ArrayBuilder
	 * depending on its ArrayType. Updates displayed Array of Rectangles
	 * accordingly.
	 */
	private class ButtonListener implements EventHandler<MouseEvent> {

		private ArrayType _type;

		public ButtonListener(ArrayType type) {
			_type = type;
		}

		@Override
		public void handle(MouseEvent e) {
			Color[][] newColors;
			switch (_type) {
				case STRIPE:
					newColors = ArrayBuilder.buildStripeArray();
					break;
				case CHECKERBOARD:
					newColors = ArrayBuilder.buildCheckerArray();
					break;
				case HALF_HALF:
					newColors = ArrayBuilder.buildTwoColorArray();
					break;
				case DIAGONAL:
					newColors = ArrayBuilder.buildDiagonalStripeArray();
					break;
				default:
					newColors = ArrayBuilder.buildStripeArray();
			}

			try {
				ArrayBuilder.displayArray(newColors, _contentPane);
			} catch (NullPointerException exception) {
				System.out.println("You must implement " + _type.getMethod() + " in the ArrayBuilder.java class!");
			}

		}
	}
}
