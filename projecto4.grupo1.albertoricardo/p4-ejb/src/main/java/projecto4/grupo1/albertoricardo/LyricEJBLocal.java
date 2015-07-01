package projecto4.grupo1.albertoricardo;

import javax.ejb.Local;






	@Local
	public interface LyricEJBLocal {

		
	    LyricEntity create(LyricEntity lyric);
	    LyricEntity update(LyricEntity lyric);
	    void remove(LyricEntity lyric);
	    LyricEntity find(Object id);
	}