package FruitNinja;

import cs015.prj.FruitNinjaSupport.CS15Fruit;

public class Pear extends Fruit {

  public Pear() {
    this.wash();
    this.ripen();
  }
  /*
  * This returns the file path of a Pear. It is used the support code to
  * display an image of a Pear.
  */
  @Override
  public String getImage() {
    return cs015.prj.FruitNinjaSupport.Constants.PEAR_PATH;
  }
  /*This method returns an int to the onBladeContact() in FruitNinja which
  * returns that value to add to the score. Every subclass of fruit returns a
  * different value.
  */
  @Override
  public int getValue() {
    return 3; //score when a pear is sliced
  }

}
