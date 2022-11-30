package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/** A JPanel that contains the player stats
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class StatsPanel extends JPanel {
    private DefaultTableModel playerTableModel;

    /**
     * Initialize a stat panel
     */
    public StatsPanel()
    {
        setOpaque(false);

        playerTableModel = new DefaultTableModel();
        playerTableModel.addColumn("PlayerInfo");
        JTable playerStatsTbl = new JTable(playerTableModel);
        playerStatsTbl.setPreferredSize(new Dimension(200, 80));

        add(playerStatsTbl, BorderLayout.CENTER);
    }


    /**
     * Update the player information
     * @param playerStats is the new player information
     */
    public void updatePlayerTable(String[] playerStats)
    {
        int rows = playerTableModel.getRowCount();

        for(int i = 0; i < rows; i++)
            playerTableModel.removeRow(0);

        for (String stats: playerStats)
            playerTableModel.insertRow(playerTableModel.getRowCount(), new Object[]{stats});
    }
}
