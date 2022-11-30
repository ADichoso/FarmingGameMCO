package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToolsPanel extends JPanel {
    private static final Color UNSELECTED_TOOL_COLOR = Color.LIGHT_GRAY;
    private static final Color SELECTED_TOOL_COLOR = Color.GRAY;

    private ArrayList<ToolButtonPanel> toolButtonPanels;
    private JButton selectedButton;

    public void setSelectedButton(JButton selectedButton)
    {
        for(ToolButtonPanel toolButtonPanel : toolButtonPanels)
            toolButtonPanel.getToolButton().setBackground(UNSELECTED_TOOL_COLOR);

        this.selectedButton = selectedButton;
        this.selectedButton.setBackground(SELECTED_TOOL_COLOR);
    }

    public void performSelectedButtonAction()
    {
        selectedButton.doClick();
    }
    public ToolsPanel(ActionListener onPlowAction, ActionListener onShovelAction, ActionListener onPickaxeAction, ActionListener onPlantAction, ActionListener onWaterAction, ActionListener onFertAction, ActionListener onHarvestAction)
    {
        //Create the main menu frame (Has the image display, title, start game button, help button, and quit button)
        setOpaque(false);

        setName("Tools");
        setLayout(new FlowLayout());

        setPreferredSize(new Dimension(150, 400));

        toolButtonPanels = new ArrayList<ToolButtonPanel>();

        ToolButtonPanel plowButtonPanel = new ToolButtonPanel(onPlowAction, "Plow:");
        ToolButtonPanel shovelButtonPanel = new ToolButtonPanel(onShovelAction, "Shovel:");
        ToolButtonPanel pickaxeButtonPanel = new ToolButtonPanel(onPickaxeAction, "Pickaxe:");
        ToolButtonPanel plantButtonPanel = new ToolButtonPanel(onPlantAction, "Plant:");
        ToolButtonPanel waterButtonPanel = new ToolButtonPanel(onWaterAction, "Water:");
        ToolButtonPanel fertilizerButtonPanel = new ToolButtonPanel(onFertAction, "Fertilizer:");
        ToolButtonPanel harvestButtonPanel = new ToolButtonPanel(onHarvestAction, "Harvest:");

        plowButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.PLOW_ICON_NAME)));
        shovelButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.SHOVEL_ICON_NAME)));
        pickaxeButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.PICKAXE_ICON_NAME)));
        plantButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.PLANT_ICON_NAME)));
        waterButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.WATER_ICON_NAME)));
        fertilizerButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.FERTILIZER_ICON_NAME)));
        harvestButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.HARVEST_ICON_NAME)));

        add(plowButtonPanel);
        add(shovelButtonPanel);
        add(pickaxeButtonPanel);
        add(plantButtonPanel);
        add(waterButtonPanel);
        add(fertilizerButtonPanel);
        add(harvestButtonPanel);

        toolButtonPanels.add(plowButtonPanel);
        toolButtonPanels.add(shovelButtonPanel);
        toolButtonPanels.add(pickaxeButtonPanel);
        toolButtonPanels.add(plantButtonPanel);
        toolButtonPanels.add(waterButtonPanel);
        toolButtonPanels.add(fertilizerButtonPanel);
        toolButtonPanels.add(harvestButtonPanel);

        for(ToolButtonPanel toolButtonPanel : toolButtonPanels)
            toolButtonPanel.getToolButton().addActionListener(e -> setSelectedButton((JButton) e.getSource()));

        setSelectedButton(toolButtonPanels.get(0).getToolButton());
    }


}
