package GUI;

import java.io.*;
import java.util.*;

import javax.sound.sampled.*;
import javax.swing.*;

/** A File that allows sound to be played in the program
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.4
 * @since 09/12/2022
 */
public class GameMusicSFX {

	/**
	 * Instantiates a game music sfx class that will play sound.
	 */
	public GameMusicSFX()
	{
		System.out.println("A sound player has been made!");
	}

	/**
	 * Play sound given a file once.
	 * @param sfxPath is the filepath to the sound file to be played
	 */
	public void playSFX(String sfxPath) {
		try {
			File sfx = new File(GameMusicSFX.class.getResource(sfxPath).toURI());
			
			if (sfx.exists()) {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(sfx));
				clip.start();
			}
			else {
				System.out.println("Can't find music file.");
			}
			
		} catch (Exception ex){
			System.out.println(ex);
		}
	}
	
	public void playRandomBG() {
		try {
			File bgMusic1 = new File(GameMusicSFX.class.getResource(MusicSFXLocation.BG_MUSIC_1_NAME).toURI());
			File bgMusic2 = new File(GameMusicSFX.class.getResource(MusicSFXLocation.BG_MUSIC_2_NAME).toURI());
			File bgMusic3 = new File(GameMusicSFX.class.getResource(MusicSFXLocation.BG_MUSIC_3_NAME).toURI());
			File bgMusic4 = new File(GameMusicSFX.class.getResource(MusicSFXLocation.BG_MUSIC_4_NAME).toURI());
			
			Random rand = new Random();
			int bgMusic = rand.nextInt(4) + 1;
			
			if (bgMusic1.exists() && bgMusic2.exists() && bgMusic3.exists() && bgMusic4.exists()) {
				Clip clip = AudioSystem.getClip();
				switch (bgMusic) {
					case 1: clip.open(AudioSystem.getAudioInputStream(bgMusic1)); break;
					case 2: clip.open(AudioSystem.getAudioInputStream(bgMusic2)); break;
					case 3: clip.open(AudioSystem.getAudioInputStream(bgMusic3)); break;
					case 4: clip.open(AudioSystem.getAudioInputStream(bgMusic4)); break;
				}
				clip.start();
				System.out.println("Music #" + bgMusic + " currently playing.");
				while (clip.getMicrosecondLength() > clip.getMicrosecondPosition()) {}
				clip.stop();
			}
			else {
				System.out.println("Can't find music file.");
			}
			
		} catch (Exception ex){
			System.out.println(ex);
		}
	}
}
