package lab1;
import cs015.labs.CupcakeSupport.*;

public class CupcakeDecorator {

  public CupcakeDecorator(Cupcake myCupcake) {
    CupcakeFrosting froster = new CupcakeFrosting();
    myCupcake.add(froster);
    CupcakeSprinkles sprinkler = new CupcakeSprinkles();
    myCupcake.add(sprinkler);
    CupcakeCherry cherrer = new CupcakeCherry();
    myCupcake.add(cherrer);
  }
}
