package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FarmerTypePanel extends JPanel {
    private DefaultTableModel farmerTypePanel;

    public FarmerTypePanel(String panelLabelText)
    {
        setPreferredSize(new Dimension(180, 300));
        JLabel headerLbl = new JLabel(panelLabelText);

        farmerTypePanel = new DefaultTableModel();
        farmerTypePanel.addColumn("TypeInfo");

        JTable farmerStatsTbl = new JTable(farmerTypePanel);
        farmerStatsTbl.setLayout(new FlowLayout());
        farmerStatsTbl.setPreferredSize(new Dimension(180, 260));
        farmerStatsTbl.setRowHeight(40);

        add(headerLbl, BorderLayout.NORTH);
        add(farmerStatsTbl, BorderLayout.CENTER);
    }


    public void updateFarmerStatsTable(String[] farmerTypeStats)
    {
        int rows = farmerTypePanel.getRowCount();

        for(int i = 0; i < rows; i++)
            farmerTypePanel.removeRow(0);

        for (String stats: farmerTypeStats)
        {
            if (farmerTypePanel.getRowCount() <= 0)
                farmerTypePanel.insertRow(0, new Object[]{stats});
            else
                farmerTypePanel.insertRow(farmerTypePanel.getRowCount(), new Object[]{stats});
        }

        rows = farmerTypePanel.getRowCount() - 1;
        farmerTypePanel.moveRow(rows, rows,0);
    }
}
