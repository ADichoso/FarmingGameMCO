package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class TileInfoPanel extends JPanel {

    private DefaultTableModel tileInfoTableModel;
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

    public void updateTileInfoTable(String[] tileInfo)
    {
        int rows = tileInfoTableModel.getRowCount();

        for(int i = 0; i < rows; i++)
            tileInfoTableModel.removeRow(0);

        for (String info: tileInfo)
            tileInfoTableModel.insertRow(tileInfoTableModel.getRowCount(), new Object[] {info});
    }
}
