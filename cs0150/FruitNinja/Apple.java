package FruitNinja;

import cs015.prj.FruitNinjaSupport.CS15Fruit;

public class Apple extends Fruit {

  public Apple() {
    this.wash();
    this.ripen();
  }
  /*
  * This returns the file path of an Apple. It is used the support code to
  * display an image of an Apple.
  */
  @Override
  public String getImage() {
    return cs015.prj.FruitNinjaSupport.Constants.APPLE_PATH;
  }
  /*This method returns an int to the onBladeContact() in FruitNinja which 
  * returns that value to add to the score. Every subclass of fruit returns a
  * different value.
  */
  @Override
  public int getValue() {
    return 1; //score when a apple is sliced
  }

}
