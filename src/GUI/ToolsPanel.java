package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** The tools panel which contains all the tools that the player can use
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class ToolsPanel extends JPanel {
    private static final Color UNSELECTED_TOOL_COLOR = Color.LIGHT_GRAY;
    private static final Color SELECTED_TOOL_COLOR = Color.GRAY;

    private ArrayList<ToolButtonPanel> toolButtonPanels;
    private JButton selectedButton;

    /**
     * set the selected button that the player recently clicked
     * @param selectedButton is the selected button
     */
    public void setSelectedButton(JButton selectedButton)
    {
        for(ToolButtonPanel toolButtonPanel : toolButtonPanels)
            toolButtonPanel.getToolButton().setBackground(UNSELECTED_TOOL_COLOR);

        this.selectedButton = selectedButton;
        this.selectedButton.setBackground(SELECTED_TOOL_COLOR);
    }

    /**
     * Perform the action set in the selected button
     */
    public void performSelectedButtonAction()
    {
        selectedButton.doClick();
    }

    /**
     * Initialize the tools panel
     * @param onPlowAction is the plow action of the player
     * @param onShovelAction is the shovel action of the player
     * @param onPickaxeAction is the pickaxe action of the player
     * @param onPlantAction is the plant action of the player
     * @param onWaterAction is the water action of the player
     * @param onFertAction is the fertilizer action of the player
     * @param onHarvestAction is the harvest action of the player
     */
    public ToolsPanel(ActionListener onPlowAction, ActionListener onShovelAction, ActionListener onPickaxeAction, ActionListener onPlantAction, ActionListener onWaterAction, ActionListener onFertAction, ActionListener onHarvestAction){
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

        try {
            plowButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.PLOW_ICON_NAME)));
            shovelButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.SHOVEL_ICON_NAME)));
            pickaxeButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.PICKAXE_ICON_NAME)));
            plantButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.PLANT_ICON_NAME)));
            waterButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.WATER_ICON_NAME)));
            fertilizerButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.FERTILIZER_ICON_NAME)));
            harvestButtonPanel.setButtonIcon(new ImageIcon(ToolsPanel.class.getResource(PictureLocations.HARVEST_ICON_NAME)));
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
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
