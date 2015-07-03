package projecto4.grupo1.albertoricardo;


import javax.ejb.Local;
import javax.xml.bind.annotation.XmlTransient;

import projecto4.grupo1.albertoricardo.entities.MusicEntity;



@Local
public interface MusicEJBLocal {

	
    MusicEntity create(MusicEntity music);
    MusicEntity update(MusicEntity music);
    void remove(MusicEntity music);
    MusicEntity find(Object id);
}