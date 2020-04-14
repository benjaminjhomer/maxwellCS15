package lab4;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaneOrganizer {

	private BorderPane _myBorderPane;
	private KeyableRect _leftRect;
	private KeyableRect _centerRect;
	private KeyableRect _rightRect;

	public PaneOrganizer() {
		_myBorderPane = new BorderPane();
		_myBorderPane.setStyle("-fx-background-color: orange;");
		this.createRectsPane();
		this.createLabelPane();
	}

	public Pane getRoot() {
		return _myBorderPane;
	}

	private void createRectsPane() {
		Pane rectsPane = new Pane();
		rectsPane.setPrefSize(Constants.RECT_PANE_PREF_WIDTH, Constants.RECT_PANE_PREF_HEIGHT);
		rectsPane.setStyle("-fx-background-color: #FFFFFF");
		_myBorderPane.setTop(rectsPane);
		_leftRect = new KeyableRect(Constants.LEFT_RECT_X, Constants.LEFT_RECT_Y, Color.RED);
		_centerRect = new KeyableRect(Constants.CENTER_RECT_X, Constants.CENTER_RECT_Y, Color.GREEN);
		_rightRect = new KeyableRect(Constants.RIGHT_RECT_X, Constants.RIGHT_RECT_Y, Color.BLUE);
		rectsPane.getChildren().addAll(_leftRect.getNode(), _rightRect.getNode(), _centerRect.getNode());
	}

	private void createLabelPane() {

		VBox labelPane = new VBox();
		_myBorderPane.setBottom(labelPane);
		Label myLabel = new Label("LAB 4");
		labelPane.getChildren().add(myLabel);
		labelPane.setAlignment(Pos.CENTER);
		labelPane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
		labelPane.setFocusTraversable(true);
	}

	private class KeyHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent e) {

			KeyCode keyPressed = e.getCode();

			switch (keyPressed) {
			
			case SPACE:
				System.out.println("Ben Homer is a God at Rainbow 6.");
				break;
			case R:
				_rightRect.changeColor();
				break;
			case C:
				_centerRect.changeColor();
				break;
			case L:
				_leftRect.changeColor();
				break;
			case Q:
				Platform.exit();
			default:
				break;
			}

			e.consume();

		}

	}
}
