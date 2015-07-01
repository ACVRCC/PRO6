package projecto4.grupo1.albertoricardo.music;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javazoom.jl.player.advanced.AdvancedPlayer;

@Named
@ApplicationScoped
public class PlayMusic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdvancedPlayer player = null;


	public PlayMusic() {
	}


	public void play(String filename) {
		try {
			File f = new File(filename);
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			AdvancedPlayer p = new AdvancedPlayer(bis);
			setPlayer(p);
			player.play();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		if (player != null) player.close();
	}


	public AdvancedPlayer getPlayer() {
		return player;
	}


	public void setPlayer(AdvancedPlayer player) {
		this.player = player;
	}

}