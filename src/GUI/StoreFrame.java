package GUI;

import GameLogic.GameSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoreFrame extends SeedsFrame {
    private JPanel cropButtonsPanel;
    public StoreFrame(ActionListener onQuitAction)
    {
        super(onQuitAction);

        setSize(800, 300);

        cropButtonsPanel = new JPanel();
        cropButtonsPanel.setPreferredSize(new Dimension(40, getHeight()));
        cropButtonsPanel.setOpaque(false);
        add(cropButtonsPanel, BorderLayout.EAST);
    }
    public void initializeCropSelectionButtons(String[] cropNames, JLabel gameMessageLabel)
    {
        for(int i = 0; i < cropNames.length; i++) {
            String cropName = cropNames[i];

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
