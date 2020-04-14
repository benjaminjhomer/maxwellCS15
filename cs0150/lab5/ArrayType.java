package lab5;

/*
 ____                      _              _ _ _     _ 
|  _ \  ___    _ __   ___ | |_    ___  __| (_) |_  | |
| | | |/ _ \  | '_ \ / _ \| __|  / _ \/ _` | | __| | |
| |_| | (_) | | | | | (_) | |_  |  __/ (_| | | |_  |_|
|____/ \___/  |_| |_|\___/ \__|  \___|\__,_|_|\__| (_)
                                                      
This is support code for the lab. You may look at it if you are interested, 
but you will not have to change anything to complete your lab.
*/

/*
 * This is an enum for the types of Arrays generated in ArrayBuilder
 */
public enum ArrayType {
	STRIPE ("buildStripeArray()"),
    CHECKERBOARD ("buildCheckerArray()"),
    HALF_HALF ("buildTwoColorArray()"),
    DIAGONAL ("buildDiagonalStripeArray()");

    private final String _method;

    ArrayType(String method) {
        _method = method;
    }

    public String getMethod() {
        return _method;
    }

}
