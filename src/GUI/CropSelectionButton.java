package GUI;

import GameLogic.Crop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** Tiles for interaction for the farming game. Contains the crops and land the player will utilize.
 * @author Aaron Dichoso & Andrei Martin
 * @version 2.1
 * @since 01/11/2022
 */
public class CropSelectionButton extends JButton{
    /**
     * Initialize the tile as not plowed, not having any tiles (No need for rocks right now)
     */
    public CropSelectionButton(ActionListener onButtonClick, String selectedCropName, float scalingFactor) {
        addActionListener(onButtonClick);
        try {
            setIcon(new ImageIcon(Renderer.class.getResource(PictureLocations.getFruitIconFileName(selectedCropName))));
            setPreferredSize(new Dimension((int)(getIcon().getIconWidth() * scalingFactor), (int) (getIcon().getIconHeight() * scalingFactor)));
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
