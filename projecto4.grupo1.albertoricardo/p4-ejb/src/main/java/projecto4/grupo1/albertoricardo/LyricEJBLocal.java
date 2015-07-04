package projecto4.grupo1.albertoricardo;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.entities.LyricEntity;






	@Local
	public interface LyricEJBLocal {

		
	    public LyricEntity create(LyricEntity lyric);
	    public LyricEntity update(LyricEntity lyric);
	    public void remove(LyricEntity lyric);
	    public LyricEntity find(Object id);
	}