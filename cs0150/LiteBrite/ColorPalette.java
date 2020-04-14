package LiteBrite;

import javafx.scene.paint.Color;
/**
 * Don't worry about the 'extends' stuff.
 * Pretend this says 'public class ColorPalette',
 * just like the class declarations shown in lecture.
 *
 * Class comments go here.
 */
public class ColorPalette extends cs015.prj.LiteBriteSupport.ColorPaletteSupport {

    // Instance variable declarations go here.
    private Color _currentColor;

    public ColorPalette() {

        // This adds a white ColorButton to the ColorPalette.
        // You may add up to ten ColorButtons to the ColorPalette.
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.WHITE);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.RED);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.ORANGE);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.YELLOW);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.GREEN);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.TEAL);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.BLUE);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.PURPLE);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.VIOLET);
        new cs015.prj.LiteBriteSupport.ColorButton(this, Color.BLACK);
        // 10 ColorButtons added
        _currentColor = Color.WHITE;
        //Sets initial to color White to initialize the variable.
    }

    /**
     * This method is called when a cs015.prj.LiteBrite.ColorButton
     * is clicked on. The javafx.scene.paint.Color is then passed in as a parameter.
     * You will never need to call this method  yourself; it is called
     * from within the support code. Implement this method to change the color
     * of new LitePegs added to the LiteBox.
     */
    public void setColor(Color newColor) {
        
    }

    // Do you need any additional methods here?

}
