package FruitNinja;

import cs015.prj.FruitNinjaSupport.CS15Fruit;
import cs015.prj.FruitNinjaSupport.Constants;

/*
* This is superclass for Apple, Pear, Peach, and Lemon. Fruits are Choppable
* and arent bombs.
*/
public abstract class Fruit extends CS15Fruit implements Choppable {

  public Fruit() {

  }
  /*
  * Returns a boolean of false to onBladeContact(). Is used to check whether to
  * return the value of the fruit if its not a bommb or to end the game if it
  * is. Fruits are not bombs.
  */
  public boolean bombOrNoBomb() {
    return false;
  }

  public abstract int getValue(); //needs this for Choppable

}
