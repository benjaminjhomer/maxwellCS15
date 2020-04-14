package lab1;

/**
 * This is the second lab where you are going to decorate a cupcake.
 * The purpose of this mini-app is to get you comfortable making java classes
 * from scratch, making instances and passing and receiving parameters.
 *
 * @author <your login here>
 */

public class App {

	public App() {
		// Initialize an instance of an undecorated cupcake
		cs015.labs.CupcakeSupport.Cupcake plainCupcake = new cs015.labs.CupcakeSupport.Cupcake();

		/*
		 * Make an instance of your class here. It needs to know about
		 * the cupcake in order to decorate the cupcake. This sounds
		 * a lot like an association... Look at the lecture notes if you don't
		 * remember how to do this.
		 */
		 CupcakeDecorator decorator = new CupcakeDecorator(plainCupcake);
	}


	/*
	 * This is the main method.  Don't worry about it, it just makes an
	 * instance of the App class.
	 *
	 * DO NOT CHANGE THIS CODE.
	 */
	public static void main(String[] args) {
		new App();
	}

}
