package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/** A JPanel which contains the information for a farmer type
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class FarmerTypePanel extends JPanel {
    private DefaultTableModel farmerTypeTblModel;

    /**
     * Return the farmer type table model used to contain the farmer type information
     * @return the farmer type table model used to contain the farmer type information.
     */
    public DefaultTableModel getFarmerTypeTblModel() {
        return farmerTypeTblModel;
    }

    /**
     * Initialize the farmer type panel
     * @param panelLabelText is the text to be displayed as the header for the table
     */
    public FarmerTypePanel(String panelLabelText)
    {

        setPreferredSize(new Dimension(180, 300));
        JLabel headerLbl = new JLabel(panelLabelText);

        farmerTypeTblModel = new DefaultTableModel();
        farmerTypeTblModel.addColumn("TypeInfo");

        JTable farmerStatsTbl = new JTable(farmerTypeTblModel);
        farmerStatsTbl.setLayout(new FlowLayout());
        farmerStatsTbl.setPreferredSize(new Dimension(180, 260));
        farmerStatsTbl.setRowHeight(50);

        add(headerLbl, BorderLayout.NORTH);
        add(farmerStatsTbl, BorderLayout.CENTER);
    }

    /**
     * Update the farmer stats table
     * @param farmerTypeStats is the information about a farmer type that the player can register to
     */
    public void updateFarmerStatsTable(String[] farmerTypeStats)
    {
        int rows = farmerTypeTblModel.getRowCount();

        //Remove the current rows in the table to replace it later on
        for(int i = 0; i < rows; i++)
            farmerTypeTblModel.removeRow(0);

        //Insert each element in the array as rows in the table
        for (String stats: farmerTypeStats)
            farmerTypeTblModel.insertRow(farmerTypeTblModel.getRowCount(), new Object[]{stats});
    }
}
