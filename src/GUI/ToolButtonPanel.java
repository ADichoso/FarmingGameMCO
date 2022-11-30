package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/** The tool button panel which has a button and label, allows users to select and use tools
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class ToolButtonPanel extends JPanel {

    private JButton toolButton;
    private JLabel textLbl;

    /**
     * Get the tool button
     * @return the tool button
     */
    public JButton getToolButton() {
        return toolButton;
    }

    /**
     * get the text label
     * @return the text label
     */
    public JLabel getTextLbl() {
        return textLbl;
    }

    /**
     * Initialize the tool button panel
     * @param onButtonClickAction is the action performed once the button is pressed
     * @param labelText is the text on the label
     */
    public ToolButtonPanel(ActionListener onButtonClickAction, String labelText)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setOpaque(false);

        toolButton = new JButton();
        toolButton.addActionListener(onButtonClickAction);

        textLbl = new JLabel(labelText);

        add(textLbl, BorderLayout.WEST);
        add(toolButton, BorderLayout.EAST);
    }

    /**
     * Set the button icon
     * @param icon is the new icon on the button
     */
    public void setButtonIcon(ImageIcon icon)
    {
        try
        {
            toolButton.setIcon(icon);
        } catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Equality for tool button
     * @param o is the object to be compared
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolButtonPanel that)) return false;
        return getToolButton().equals(that.getToolButton()) && getTextLbl().equals(that.getTextLbl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToolButton(), getTextLbl());
    }
}
