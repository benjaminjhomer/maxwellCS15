package FruitNinja;

import cs015.prj.FruitNinjaSupport.CS15Bomb;

public class Bomb extends CS15Bomb implements Choppable{

  private int _myValue;

  public Bomb() {

  }
  /*
  * Returns a boolean of true to onBladeContact(). This will cause the bomb to
  * explode() and end the game.
  */
  public boolean bombOrNoBomb() {
    return true;
  }
  /*
  * Returns an int to onBladeContact to add to the score. Since this is bomb, it
  * will return 0.
  */
  @Override
  public int getValue() {
    return 0;
    //Score when a bomb is sliced. Needs to return int so just returns 0.
  }
}
