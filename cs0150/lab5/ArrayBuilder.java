package lab5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;

/*
 * This class has methods to build and display arrays with different patterns.
 * You can use the buttons in the GUI to test the different methods.
 */
public class ArrayBuilder {

	/*
	 * WRITE THIS METHOD FIRST, before starting to build the color arrays!
     *
	 * This method is called in ArrayVisualizer, so you do not need to call it yourself.
	 */
	public static void displayArray(Color[][] myArray, Pane parentPane) {

		/* This generates a 2D array of Rectangles whose colors match the corresponding
		 * Color in myArray. (i.e. if the Color at myArray[1][2] is Color.WHITE, the
		 * Rectangle at rects[1][2] will be white)
         */
		Rectangle[][] rects = ArrayVisualizer.generateSquares(myArray);
		parentPane.getChildren().clear();
		// TODO: display the array here!
		for (int i = 0; i < rects.length; i++) {
			for (int j = 0; j < rects[0].length; j++) {
				parentPane.getChildren().add(rects[i][j]);
			}
		}
	}

	/**
	 * Returns a 16 by 10 2D array of Colors.
	 * The array should consist of vertical stripes of alternating LIGHT_GRAY
	 * and WHITE.
     *
     * A note about modulo: a % b computes the remainder after a is divided by b
     *
     * We can use this behaviour to test for even numbers- if i % 2 is 0, then we
     * know that i is even. If i % 2 is 1, then we know that i is odd.
	 */
	public static Color[][] buildStripeArray() {
		Color[][] myArray = new Color[16][10]; // create array
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray[1].length; j++) { // for each index (i, j)
				if (i % 2 == 0) { // if i is even, store LIGHT_GRAY
					myArray[i][j] = Color.LIGHTGRAY;
				} else { // if i is odd, store WHITE
					myArray[i][j] = Color.WHITE;
				}
			}
		}
		return myArray;
	}


	/**
	 * Returns an 8 by 8 2D array of Colors.
	 * The array should be patterned like a checkerboard, alternating RED and
	 * BLACK such that no two adjacent indices contain the same color. See the
	 * handout for a picture.
	 */
	public static Color[][] buildCheckerArray() {
		// TODO: Fill in this method and return a 2D array of Colors!
		Color[][] myArray = new Color[8][8]; // create array
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray[1].length; j++) { // for each index (i, j)
				if ((i % 2 == 0 && j % 2 ==0)||((i % 2 == 1 && j % 2 ==1))) { 
					myArray[i][j] = Color.RED;
				} else { 
					myArray[i][j] = Color.BLACK;
				}
			}
		}
		return myArray;
	}

	/**
	 * Returns a 7 by 4 (7 rows, 4 columns) 2D array of Colors.
	 * The left half of the array (lower x-indices) should be CYAN and the
	 * right half (higher x-indices) should be BLUE. See the handout for a
	 * picture.
	 */
	public static Color[][] buildTwoColorArray() {
		// TODO: Fill in this method and return a 2D array of Colors!
		Color[][] myArray = new Color[7][4]; // create array
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray[1].length; j++) { // for each index (i, j)
				if (j < 2) { 
					myArray[i][j] = Color.CYAN;
				} else { 
					myArray[i][j] = Color.BLUE;
				}
			}
		}
		return myArray;
	}

	/**
	 * Returns a 5 by 5 2D array of Colors.
	 * The array should be completely MAGENTA except for a single diagonal
	 * stripe that extends from the top-left (0, 0) to the bottom-right (4, 4)
	 * and is colored YELLOW. See the handout for a picture.
	 */
	public static Color[][] buildDiagonalStripeArray() {
		// TODO: Fill in this method and return a 2D array of Colors!
		Color[][] myArray = new Color[5][5]; // create array
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray[1].length; j++) { // for each index (i, j)
				if (j == i) { 
					myArray[i][j] = Color.YELLOW;
				} else { 
					myArray[i][j] = Color.MAGENTA;
				}
			}
		}
		return myArray;
	}
}
