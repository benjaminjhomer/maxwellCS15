package FruitNinja;

import cs015.prj.FruitNinjaSupport.CS15Game;
import cs015.prj.FruitNinjaSupport.Blade;
import java.lang.Math;

/*
* This class is the top level class and sets of the game with startGame() and
* addBlade(). It has two methods unique methods, launchItem() and
* onBladeContact().
*/

public class FruitNinja extends CS15Game {

    public FruitNinja() {
        Blade myBlade = new Blade(); //creates Blade
        myBlade.setName("Excalibur"); //names Blade
        this.startGame(); //starts game
        this.addBlade(myBlade); //adds Blade
    }
    /*Following mehtod launches item with a 30% chance of Apple and 20% for
    * Pear, Lemon, and Bomb. There is a 10% chance for Peach. It does this by
    * generating a random int from 0-9. Each int returns a different type of
    * Choppable. It is called by the support code.
    */
    public Choppable launchItem() {
        int rand = (int) (Math.random() * 10); //generate int from 0-9
        switch (rand) {

            case 0: case 1: case 2:
            Choppable myApple = new Apple();
            return myApple;

            case 3: case 4:
            Choppable myPear = new Pear();
            return myPear;

            case 5: case 6:
            Choppable myBomb = new Bomb();
            return myBomb;

            case 7: case 8:
            Choppable myLemon = new Lemon();
            return myLemon;

            case 9:
            Choppable myPeach = new Peach();
            return myPeach;

        }
        return null;
    }
    /*
    * When the blade hits something, this determines what will happen (e.g.
    * adds to score). If the object is a bomb, the game will end (still return
    * 0 because method need to return an int). If the object is a fruit, will
    * call getValue() and return how much the score should increase by.
    */
    public int onBladeContact() {
        Choppable myChoppedObject = this.getChoppable(); //Finds last object the blade touched

        //Checks if object is a bomb. If it is, ends game, if not, adds to score.
        if (myChoppedObject.bombOrNoBomb()) {
          Bomb myBomb = this.getChoppable();
          // declares type as bomb so we have access to explode()
          myBomb.explode();
          //ends game
          return 0;
          //needs to return int
        } else {
          Fruit myFruit = this.getChoppable();
          // declares type as bomb so we have access to splash() and
          //chopGraphically().
          myFruit.splash();
          myFruit.chopGraphically();
          return myChoppedObject.getValue();
          // adds value of fruit to score
        }

    }
}
