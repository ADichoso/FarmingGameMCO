package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/** A JFrame that displays the seeds information to the user
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class SeedsFrame extends ParentFrame {

    protected DefaultTableModel seedsTableModel;

    /**
     * Initialize the seed frame
     * @param onQuitAction is the action performed when the player exits the seeds frame
     */
    public SeedsFrame(ActionListener onQuitAction)
    {
        super();
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setName("GATHER SUN");
        setSize(720, 360);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel storeName = new JLabel("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-SEED STORE-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        JPanel storePanel = new JPanel();

        seedsTableModel = new DefaultTableModel();
        JTable seedsTbl = new JTable(seedsTableModel);
        seedsTbl.setRowHeight(20);
        storePanel.add(storeName, BorderLayout.NORTH);
        storePanel.add(seedsTbl.getTableHeader(), BorderLayout.NORTH);
        storePanel.add(seedsTbl, BorderLayout.CENTER);
        storePanel.setOpaque(false);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(onQuitAction);

        add(storePanel, BorderLayout.CENTER);
        add(exitButton, BorderLayout.SOUTH);
    }

    /**
     * Update the seeds store with new information
     * @param seedStore is the information about the seeds
     */
    public void updateStore(String[][] seedStore)
    {
        int rows = seedsTableModel.getRowCount();

        for(int i = 0; i < rows; i++)
            seedsTableModel.removeRow(0);

        for (String[] storeRow: seedStore)
            seedsTableModel.insertRow(seedsTableModel.getRowCount(), storeRow);
    }

    /**
     * Add the store headers to the table
     * @param seedColumns are the columns headers in the table
     */
    public void addStoreHeaders(String[] seedColumns)
    {
        if(seedsTableModel.getColumnCount() == 0)
            for(int i = 0; i < seedColumns.length; i++)
                seedsTableModel.addColumn(seedColumns[i]);
    }
}
