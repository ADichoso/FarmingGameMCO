package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/** The tile info panel which shows the tile information in a panel
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class TileInfoPanel extends JPanel {

    private DefaultTableModel tileInfoTableModel;

    /**
     * Initialize the tile info panel
     */
    public TileInfoPanel()
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setName("Tile Information");
        setPreferredSize(new Dimension(300, 400));

        setOpaque(false);

        tileInfoTableModel = new DefaultTableModel();
        tileInfoTableModel.addColumn("Tile Information");
        JTable tileInfoTbl = new JTable(tileInfoTableModel);
        tileInfoTbl.setPreferredSize(new Dimension(200, 200));

        add(tileInfoTbl, BorderLayout.CENTER);
    }

    /**
     * Update the tile info table
     * @param tileInfo is the array of strings containing the new tile information
     */
    public void updateTileInfoTable(String[] tileInfo)
    {
        int rows = tileInfoTableModel.getRowCount();

        for(int i = 0; i < rows; i++)
            tileInfoTableModel.removeRow(0);

        for (String info: tileInfo)
            tileInfoTableModel.insertRow(tileInfoTableModel.getRowCount(), new Object[] {info});
    }
}
