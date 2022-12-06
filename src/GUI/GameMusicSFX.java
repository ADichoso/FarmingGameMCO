package GUI;

import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class GameMusicSFX {
	
	public void playSFX(String sfxPath) {
		try {
			File sfx = new File(sfxPath);
			
			if (sfx.exists()) {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(sfx));
				clip.start();
				while (clip.getMicrosecondLength() < clip.getMicrosecondPosition()) {}
			}
			else {
				System.out.println("Cant find");
			}
			
		} catch (Exception ex){
			System.out.println(ex);
		}
	}
}

/*
try {
    plowButtonPanel.setButtonSFX(MusicSFXLocation.PLOW_SFX_NAME);
    shovelButtonPanel.setButtonSFX(MusicSFXLocation.SHOVEL_SFX_NAME);
    pickaxeButtonPanel.setButtonSFX(MusicSFXLocation.PICKAXE_SFX_NAME);
    plantButtonPanel.setButtonSFX(MusicSFXLocation.PLANT_SFX_NAME);
    waterButtonPanel.setButtonSFX(MusicSFXLocation.WATER_SFX_NAME);
    // fertilizerButtonPanel.setButtonSFX(PictureLocations.PLOW_ICON_NAME);
    harvestButtonPanel.setButtonSFX(MusicSFXLocation.HARVEST_SFX_NAME);
} catch (Exception ex)
{
    System.out.println(ex);
}

GameMusicSFX sfxPlayer = new GameMusicSFX();

this.selectedButton = selectedButton;
this.selectedButton.setBackground(SELECTED_TOOL_COLOR);

for (ToolButtonPanel toolButtonPanel : toolButtonPanels) {
    if (toolButtonPanel.getToolButton() == selectedButton) {
    	sfxPlayer.playSFX(toolButtonPanel.getSFXPath());
    	// System.out.println(toolButtonPanel.getSFXPath());
    }
}
*/
