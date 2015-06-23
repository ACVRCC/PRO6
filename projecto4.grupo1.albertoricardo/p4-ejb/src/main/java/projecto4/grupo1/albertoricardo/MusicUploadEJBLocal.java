package projecto4.grupo1.albertoricardo;


import java.util.Date;

import javax.ejb.Local;



@Local
public interface MusicUploadEJBLocal {

	void uploadMusicDB(String title, String artist, String album,
			Date dateReleased, String path, UserEntity userEntity);
	
}