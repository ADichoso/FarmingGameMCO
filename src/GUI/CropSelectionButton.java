package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** A JButton used to select crops that the player can plant
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class CropSelectionButton extends JButton{

    /**
     * Initialize the crop selection button with its action
     * @param onButtonClick is the action performed when the crop selection button is pressed
     * @param selectedCropName is the name of the crop selected
     * @param scalingFactor is the scaling factor for the button's size
     */
    public CropSelectionButton(ActionListener onButtonClick, String selectedCropName, float scalingFactor) {
        addActionListener(onButtonClick);

        try
        {
            setIcon(new ImageIcon(GUISystem.class.getResource(PictureLocations.getFruitIconFileName(selectedCropName)))); //Set the image of the button
            setPreferredSize(new Dimension((int)(getIcon().getIconWidth() * scalingFactor), (int) (getIcon().getIconHeight() * scalingFactor)));
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
