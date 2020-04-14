package lab4;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class KeyableRect {
	private Rectangle _myRect;
	public KeyableRect(double x, double y, Color myColor) {
		_myRect = new Rectangle(x, y, Constants.RECT_WIDTH, Constants.RECT_HEIGHT);
		_myRect.setFill(myColor);
	}
	public Node getNode() {
		return _myRect;
	}
	
	public void changeColor() {
		int r = (int) (255*Math.random());
		int g = (int) (255*Math.random());
		int b = (int) (255*Math.random());
		Color newC = Color.rgb(r,g,b);
		_myRect.setFill(newC);
	}
}
