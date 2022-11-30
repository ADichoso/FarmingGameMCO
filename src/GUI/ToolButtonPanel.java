package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ToolButtonPanel extends JPanel {

    private JButton toolButton;
    private JLabel textLbl;

    public JButton getToolButton() {
        return toolButton;
    }

    public JLabel getTextLbl() {
        return textLbl;
    }

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
