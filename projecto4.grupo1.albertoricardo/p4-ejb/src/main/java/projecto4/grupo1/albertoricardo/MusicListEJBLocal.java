package projecto4.grupo1.albertoricardo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.xml.bind.annotation.XmlTransient;

import projecto4.grupo1.albertoricardo.entities.MusicEntity;
import projecto4.grupo1.albertoricardo.entities.UserEntity;



@Local
public interface MusicListEJBLocal {

	List<MusicEntity> listMusics();

	List<MusicEntity> listOwnMusics(UserEntity user);

	boolean update(MusicEntity music);

	boolean removerUserOwnership(UserEntity user);

	void removerMusicUserOwnership(MusicEntity m, UserEntity user);

	ArrayList<MusicEntity> getOwnMusics(int id);

	ArrayList<MusicEntity> getMusicsFromId(int id);

	MusicEntity getMusicFromId(int id);
	
}