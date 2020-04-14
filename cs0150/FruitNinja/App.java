package FruitNinja;

import javafx.application.Application;
import javafx.stage.Stage;
import cs015.prj.FruitNinjaSupport.FruitNinjaFrame;

/**
 * Welcome to your FruitNinja App class. In the constructor you should
 * instantiate your top-level object.
 *
 * Sets up FruitNinja as top level class which will start the game. It also
 * takes in a Stage. 
 */

public class App extends Application{

	@Override
	public void start(Stage stage)  {
		FruitNinjaFrame frame = new FruitNinjaFrame(stage);
		// Your code goes below!!!
		FruitNinja myFruitNinja = new FruitNinja();
		frame.addGame(myFruitNinja);
	}

	/*
	 * This is called a mainline. This is the code that is executed when you run
	 * the program from the command line. The outer graphical frame of
	 * FruitNinja is a special type of graphical object that does not get garbage
	 * collected until you close the application. This helps explain why the
	 * application does not go out of scope!
	 *
	 * Calling launch below will cause the Fruit Ninja Frame to appear.
	 *
	 * Please, DO NOT CHANGE THIS CODE!
	 *
	 */
	public static void main(String[] argv) {
		launch(argv);
	}

}
