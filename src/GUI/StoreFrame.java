package GUI;

import GameLogic.GameSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreFrame extends JFrame {

    private DefaultTableModel storeTableModel;
    public StoreFrame(ActionListener onQuitAction)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setName("GATHER SUN");
        setSize(720, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel storeName = new JLabel("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-SEED STORE-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        JPanel storePanel = new JPanel();

        storeTableModel = new DefaultTableModel();
        JTable storeTbl = new JTable(storeTableModel);

        storePanel.add(storeName, BorderLayout.NORTH);
        storePanel.add(storeTbl.getTableHeader(), BorderLayout.NORTH);
        storePanel.add(storeTbl, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(onQuitAction);

        add(storePanel, BorderLayout.CENTER);
        add(exitButton, BorderLayout.SOUTH);
    }

    public void updateStore(String[][] seedStore)
    {
        int rows = storeTableModel.getRowCount();

        for(int i = 0; i < rows; i++)
        {
            storeTableModel.removeRow(0);
        }

        for (String[] storeRow: seedStore)
        {
            storeTableModel.insertRow(0, storeRow);
        }
    }

    public void addStoreHeaders(String[] seedColumns)
    {
        if(storeTableModel.getColumnCount() == 0)
            for(int i = 0; i < seedColumns.length; i++)
                storeTableModel.addColumn(seedColumns[i]);
    }
}
