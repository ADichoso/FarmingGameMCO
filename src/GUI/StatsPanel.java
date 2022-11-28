package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatsPanel extends JPanel {
    private DefaultTableModel playerTableModel;

    public StatsPanel()
    {

        playerTableModel = new DefaultTableModel();
        playerTableModel.addColumn("PlayerInfo");
        JTable playerStatsTbl = new JTable(playerTableModel);
        playerStatsTbl.setPreferredSize(new Dimension(200, 80));

        add(playerStatsTbl, BorderLayout.CENTER);
    }


    public void updatePlayerTable(String[] playerStats)
    {
        int rows = playerTableModel.getRowCount();

        for(int i = 0; i < rows; i++)
            playerTableModel.removeRow(0);

        for (String stats: playerStats)
        {
            if (playerTableModel.getRowCount() <= 0)
                playerTableModel.insertRow(0, new Object[]{stats});
            else
                playerTableModel.insertRow(playerTableModel.getRowCount(), new Object[]{stats});
        }

        rows = playerTableModel.getRowCount() - 1;
        playerTableModel.moveRow(rows, rows,0);
    }
}
