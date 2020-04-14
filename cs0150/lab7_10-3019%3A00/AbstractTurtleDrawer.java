package lab7;

import cs015.labs.PseudocodeSupport.*;

/**
 * DO NOT EDIT THIS CLASS. THIS CLASS IS FOR DEBUGGING PURPOSES ONLY.
 * 
 * You can go through these methods and add some printlines, but do not
 * delete any of the original code.
 */

public abstract class AbstractTurtleDrawer extends TurtleDrawer {
		
		// Empty constructor. Does nothing.
		public AbstractTurtleDrawer() { 
			
		}
		
		// Calculates the correct index based on user's login.
		public int getIndex(String username) {
			char[] userchars = username.toCharArray();
			int sum = 0;
			for (char c: userchars) {
				sum += (int) c;
			}
			System.out.println(sum % 10);
			return sum % 10;
		}
		
		// Uses the user login to get a password from _passwords.
		// The same password will be obtained every time (for a given login).
		public String getPassword(String username) {
			if (username.equals("<your login>")) { // DO NOT EDIT THIS CODE
				System.out.println("Please make sure you change \"<your login>\" on line 14 of MyTurtleDrawer. Exiting app.\n");
				System.exit(1);
			}
			
			return super.getPassword(username); // Defined in the cs015.jar. You cannot step into this method.
		}

		public void lockUserOut(boolean passwordWasCorrect) {
			if (!passwordWasCorrect) {
				System.out.println("Password was incorrect, now exiting app.\n");
				System.exit(1);
			}
			System.out.println("Yes! You got the right password!");
		}
		
		/**
		 * DO NOT EDIT THIS CODE!!
		 *
		 * Takes in an input username (login) and password. It uses the 
		 * login to obtain the correct password, then checks whether the 
		 * user given password is the same as the correct password.
		 * 
		 * Hint: Is there any way to use the debugger to figure out the value of 
		 * correctPassword?
		 */
		public void checkPassword(String username, String password) {
			String correctPassword = this.getPassword(username);
			boolean passwordCheck = password.equals(correctPassword);
			this.lockUserOut(passwordCheck);
		}
		
		public abstract void drawSquares(Turtle turtle, double sideLength);

}
