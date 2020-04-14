package FruitNinja;

import cs015.prj.FruitNinjaSupport.CS15Fruit;

public class Peach extends Fruit {

  public Peach() {
    this.wash();
    this.ripen();
  }
  /*
  * This returns the file path of a Peach. It is used the support code to
  * display an image of a Peach.
  */
  @Override
  public String getImage() {
    return cs015.prj.FruitNinjaSupport.Constants.PEACH_PATH;
  }
  /*This method returns an int to the onBladeContact() in FruitNinja which
  * returns that value to add to the score. Every subclass of fruit returns a
  * different value.
  */
  @Override
  public int getValue() {
    return 4; //score when a peach is sliced
  }

}
