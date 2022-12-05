package GUI;

import GameLogic.GameSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** A JFrame that displays the seeds information to the user and allows them to buy seeds to plant
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class StoreFrame extends SeedsFrame {
    private JPanel cropButtonsPanel;

    /**
     * Initialize the store frame
     * @param onQuitAction is the action performed when the player wants to quit
     */
    public StoreFrame(ActionListener onQuitAction)
    {
        super(onQuitAction);

        setSize(800, 400);

        cropButtonsPanel = new JPanel();
        cropButtonsPanel.setPreferredSize(new Dimension(40, getHeight()));
        cropButtonsPanel.setOpaque(false);
        add(cropButtonsPanel, BorderLayout.EAST);
    }

    /**
     * Initialize the crop selection buttons used to select crops
     * @param cropNames are the names of the crops that the player can select
     * @param gameMessageLabel is the label which shows the message to the player after performing an action
     */
    public void initializeCropSelectionButtons(String[] cropNames, JLabel gameMessageLabel)
    {
        for(int i = 0; i < cropNames.length; i++) {
            String cropName = cropNames[i];

            //Player can select a crop if they click the button
            CropSelectionButton cropSelectionButton = new CropSelectionButton
                    (
                            e ->
                            {
                                GameSystem.selectCrop(cropName);
                                gameMessageLabel.setText(GameSystem.onPlant());
                            },
                            cropName, 1.5f
                    );
            cropButtonsPanel.add(cropSelectionButton);
        }
    }
}
