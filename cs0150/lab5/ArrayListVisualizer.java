package lab5;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

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
 * This class creates a visualizer for the ArrayListBuilder to display the given squares
 */
public class ArrayListVisualizer {
	private Tab _tab;
	private ArrayListBuilder _arrayListBuilder;
	private Pane _contentPane;

	public ArrayListVisualizer() {
		_tab = new Tab("ArrayLists");
		_tab.setContent(this.createContent());
		_arrayListBuilder = new ArrayListBuilder();
	}

	/*
	 * This creates the root pane and adds the control Pane and content
	 * Pane to it
	 */
	private Node createContent() {
		BorderPane pane = new BorderPane();
		pane.setPrefSize(Constants.PANEL_W,
				Constants.PANEL_H + Constants.CP_HEIGHT);
		pane.setBottom(this.createControlPane());
		_contentPane = new Pane();
		pane.setTop(_contentPane);
		return pane;
	}

	/*
	 * This creates the control Pane with the buttons to control the
	 * displayed squares.
	 */
	private Pane createControlPane() {
		VBox controlPane = new VBox(5);
		Button addPinkButton = new Button("Add Pink");
		Button addWhiteButton = new Button("Add White");
		Button addThreePinkButton = new Button("Add Three Pink");
		Button clearButton = new Button("Clear");
		Button pinkToBlueButton = new Button("Change Pinks to Blues");
		Button removeThirdButton = new Button("Remove Third");

		addPinkButton.setOnMouseReleased(
			new ButtonListener(ActionType.ADD_PINK));
		addWhiteButton.setOnMouseReleased(
			new ButtonListener(ActionType.ADD_WHITE));
		addThreePinkButton.setOnMouseReleased(
			new ButtonListener(ActionType.ADD_THREE_PINK));
		pinkToBlueButton.setOnMouseReleased(
			new ButtonListener(ActionType.PINK_TO_BLUE));
		clearButton.setOnMouseReleased(
			new ButtonListener(ActionType.CLEAR));
		removeThirdButton.setOnMouseReleased(
			new ButtonListener(ActionType.REMOVE_THIRD));

        HBox topBox = new HBox(5);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(5, 5, 0, 5));
        topBox.getChildren().addAll(addPinkButton, addWhiteButton,
                addThreePinkButton);

        HBox botBox = new HBox(5);
        botBox.setAlignment(Pos.CENTER);
        botBox.setPadding(new Insets(0, 5, 5, 5));
        botBox.getChildren().addAll(pinkToBlueButton, removeThirdButton,
                clearButton);

		controlPane.getChildren().addAll(topBox, botBox);
		return controlPane;
	}

	/*
	 * This method returns the ArrayListVisualizer tab.
	 */
	public Tab toTab(){
		return _tab;
	}

	/*
	 * Given an ArrayList of colors, this method generates an ArrayList of 
	 * Rectangles with colors based on the passed in ArrayList of colors.
	 * This method is used in the ArrayListBuilder class
	 */
	public static ArrayList<Rectangle> generateSquares(ArrayList<Color> colors) {

		ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

		if (colors.size() == 0) {
			return rects;
		}

		int maxSquareWidth =
			Constants.PANEL_W / colors.size() - Constants.BORDER * 2;
		int squareSide =
			Math.min(maxSquareWidth, Constants.DEFAULT_SQUARE_SIDE);

		for (int i = 0; i < colors.size(); i++) {
			Rectangle newSquare = new Rectangle(
					squareSide, squareSide, colors.get(i));
			newSquare.setStrokeWidth(Constants.BORDER);
			newSquare.setStroke(Color.BLACK);
			int squareX = i * (squareSide + 1) +
					(Constants.PANEL_W - colors.size() * (squareSide + 1)) / 2;
			int squareY = (Constants.PANEL_H - (squareSide + 1)) / 2;
			newSquare.setX(squareX);
			newSquare.setY(squareY);
			rects.add(newSquare);
		}

		return rects;
	}

	/**
	 * Listener that calls appropriate method of student's ArrayListBuilder
	 * depending on its ActionType. Updates displayed ArrayList of Rectangles
	 * accordingly.
	 */
	private class ButtonListener implements EventHandler<MouseEvent> {
		ActionType _type;

		public ButtonListener(ActionType type) {
			_type = type;
		}

		@Override
		public void handle(MouseEvent e) {
			e.consume();
			switch(_type) {
			case ADD_WHITE:
				_arrayListBuilder.addWhite();
				break;
			case ADD_PINK:
				_arrayListBuilder.addPink();
				break;
			case ADD_THREE_PINK:
				_arrayListBuilder.addThreePink();
				break;
			case CLEAR:
				_arrayListBuilder.clear();
				break;
			case REMOVE_THIRD:
				_arrayListBuilder.removeThird();
				break;
			case PINK_TO_BLUE:
				_arrayListBuilder.changePinksToBlues();
				break;
			}
			_arrayListBuilder.displayArrayList(_contentPane);
		}
	}
}
