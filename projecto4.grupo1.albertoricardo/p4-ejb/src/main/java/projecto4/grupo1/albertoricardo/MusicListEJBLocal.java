package projecto4.grupo1.albertoricardo;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.xml.bind.annotation.XmlTransient;

import projecto4.grupo1.albertoricardo.MusicEntity;



@Local
public interface MusicListEJBLocal {
	@XmlTransient
	List<MusicEntity> listMusics();
	@XmlTransient
	List<MusicEntity> listOwnMusics(UserEntity user);

	boolean update(MusicEntity music);

	boolean removerUserOwnership(UserEntity user);

	void removerMusicUserOwnership(MusicEntity m, UserEntity user);
	
}