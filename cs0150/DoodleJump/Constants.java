package DoodleJump;

/**
 * This is your Constants class. It defines some constants you will need
 * in DoodleJump, using the default values from the demo--you shouldn't
 * need to change any of these values unless you want to experiment. Feel
 * free to add more constants to this class!
 *
 * A NOTE ON THE GRAVITY CONSTANT:
 *   Because our y-position is in pixels rather than meters, we'll need our
 *   gravity to be in units of pixels/sec^2 rather than the usual 9.8m/sec^2.
 *   There's not an exact conversion from pixels to meters since different
 *   monitors have varying numbers of pixels per inch, but assuming a fairly
 *   standard 72 pixels per inch, that means that one meter is roughly 2800
 *   pixels. However, a gravity of 2800 pixels/sec2 might feel a bit fast. We
 *   suggest you use a gravity of about 1000 pixels/sec2. Feel free to change
 *   this value, but make sure your game is playable with the value you choose.
 */
public class Constants {

    public static final int GRAVITY = 1000; // acceleration constant (UNITS: pixels/s^2)
    public static final int REBOUND_VELOCITY = -500; // initial jump velocity (UNITS: pixels/s)
    public static final double DURATION = 0.016; // KeyFrame duration (UNITS: s)

    public static final int PLATFORM_WIDTH = 40; // (UNITS: pixels)
    public static final int PLATFORM_HEIGHT = 10; // (UNITS: pixels)
    public static final int DOODLE_WIDTH = 20; // (UNITS: pixels)
    public static final int DOODLE_HEIGHT = 40; // (UNITS: pixels)
    
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 700;
    
    public static final int BOTTOM_HEIGHT = 175;
    public static final int LABEL_HEIGHT = 25;
    
    public static final double FIRST_LEDGE_X = 180;
    public static final double FIRST_LEDGE_Y = 510;
    public static final int HALF_WAY = 250; 
    public static final int BOTTOM = 515;
    
    public static final int MIN_SPACING = 50;
    public static final int X_MAX_VARIATION = 50;
    public static final int Y_MAX_VARIATION = 40;
    public static final int LEFT_EDGE = 0;
    public static final int RIGHT_EDGE = 400;
    public static final int DOOD_START_X = 190;
    public static final int DOOD_START_Y = 450;
}
