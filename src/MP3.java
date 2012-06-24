

import java.io.BufferedInputStream;
import java.io.FileInputStream;


import javazoom.jl.player.Player;

public class MP3 {
	private String filename=null;

	private Player player=null;

	// constructor that takes the name of an MP3 file
	public MP3(String filename) {
		this.filename = filename;
	}

	//public  int pause(){
	//	int position = 0;
	//	if (player != null)
	//	position=	player.getPosition();
	//	return position;
	//}
	public void pause(){
		if (player != null)
			player.pause();
	}
	public void resume(){
		player.resume();
	}
	public void close() {
		if (player != null)
			player.close();
	}

	// play the MP3 file to the sound card
	public void play() {
		try {
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println("Problem playing file " + filename);
			System.out.println(e);
		}

		// run in new thread to play in background
		new Thread() {
			public void run() {
				try {
					player.play();
					if (player.isComplete() == true) {
						player.close();
						myplayer.playNext();    //inform to play the next song;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}

}