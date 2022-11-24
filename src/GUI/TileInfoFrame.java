package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class TileInfoFrame extends JFrame {

    private DefaultTableModel tileInfoTableModel;
    public TileInfoFrame(ActionListener onQuitAction)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setName("Tile Information");
        setTitle("Tile Information");
        setSize(300, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel tileInfoPanel = new JPanel();
        tileInfoPanel.setLayout(new FlowLayout());

        tileInfoTableModel = new DefaultTableModel();
        tileInfoTableModel.addColumn("Tile Information");
        JTable tileInfoTbl = new JTable(tileInfoTableModel);
        tileInfoTbl.setPreferredSize(new Dimension(200, 300));


        tileInfoPanel.add(tileInfoTbl);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(onQuitAction);

        add(tileInfoPanel, BorderLayout.CENTER);
        add(exitButton, BorderLayout.SOUTH);
    }

    public void updateTileInfoTable(String[] tileInfo)
    {
        int rows = tileInfoTableModel.getRowCount();
        for(int i = 0; i < rows; i++)
            tileInfoTableModel.removeRow(0);

        for (String info: tileInfo)
        {
            tileInfoTableModel.insertRow(tileInfoTableModel.getRowCount(), new Object[] {info});
        }
    }
}
