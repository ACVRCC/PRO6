package projecto4.grupo1.albertoricardo;


import java.util.Date;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.entities.UserEntity;



@Local
public interface MusicUploadEJBLocal {


	void uploadMusicDB(String title, String artist, String album,
			Date dateReleased, String path, UserEntity ue);
	
}