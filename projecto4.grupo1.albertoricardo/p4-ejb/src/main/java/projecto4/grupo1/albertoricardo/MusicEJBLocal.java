package projecto4.grupo1.albertoricardo;


import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.MusicEntity;



@Local
public interface MusicEJBLocal {

	
    MusicEntity create(MusicEntity music);
    MusicEntity update(MusicEntity music);
    void remove(MusicEntity music);
    MusicEntity find(Object id);
}