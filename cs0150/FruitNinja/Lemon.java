package FruitNinja;

import cs015.prj.FruitNinjaSupport.CS15Fruit;

public class Lemon extends Fruit{

  public Lemon() {
    this.wash();
    this.ripen();
  }
  /*
  * This returns the file path of a Lemon. It is used the support code to
  * display an image of a Lemon.
  */
  @Override
  public String getImage() {
    return cs015.prj.FruitNinjaSupport.Constants.LEMON_PATH;
  }
  /*This method returns an int to the onBladeContact() in FruitNinja which
  * returns that value to add to the score. Every subclass of fruit returns a
  * different value.
  */
  @Override
  public int getValue() {
    return 2; //score when a lemon is sliced
  }

}
