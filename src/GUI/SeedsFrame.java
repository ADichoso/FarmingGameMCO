package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class SeedsFrame extends JFrame {

    protected DefaultTableModel seedsTableModel;
    public SeedsFrame(ActionListener onQuitAction)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setName("GATHER SUN");
        setSize(720, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel storeName = new JLabel("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-SEED STORE-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        JPanel storePanel = new JPanel();

        seedsTableModel = new DefaultTableModel();
        JTable seedsTbl = new JTable(seedsTableModel);

        storePanel.add(storeName, BorderLayout.NORTH);
        storePanel.add(seedsTbl.getTableHeader(), BorderLayout.NORTH);
        storePanel.add(seedsTbl, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(onQuitAction);

        add(storePanel, BorderLayout.CENTER);
        add(exitButton, BorderLayout.SOUTH);
    }

    public void updateStore(String[][] seedStore)
    {
        int rows = seedsTableModel.getRowCount();


        for(int i = 0; i < rows; i++)
        {
            seedsTableModel.removeRow(0);
        }

        for (String[] storeRow: seedStore)
        {
            if (seedsTableModel.getRowCount() <= 0)
                seedsTableModel.insertRow(0, storeRow);
            else
                seedsTableModel.insertRow(seedsTableModel.getRowCount() - 1, storeRow);
        }

        rows = seedsTableModel.getRowCount() - 1;
        seedsTableModel.moveRow(rows, rows,0);
    }

    public void addStoreHeaders(String[] seedColumns)
    {
        if(seedsTableModel.getColumnCount() == 0)
            for(int i = 0; i < seedColumns.length; i++)
                seedsTableModel.addColumn(seedColumns[i]);
    }
}
