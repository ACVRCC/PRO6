package projecto4.grupo1.albertoricardo.lyric;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.LyricEditorEJB;
import projecto4.grupo1.albertoricardo.user.UserLogged;



@Named
@ViewScoped
public class LyricEditor implements Serializable{


	private static final long serialVersionUID = 1L;


	@EJB
	private LyricEditorEJB le;
	@Inject
	private UserLogged userlogged;

	private String lyricTemp=null;
	private String lyricEdited=null;
	private int musicIdTemp=0;
	

	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(LyricEditor.class);

	
	
	public void uploadLyric(int idMusic){
		le.uploadLyricDB(idMusic);			
	}
	
	public void newUploadLyric(){
		le.newUploadLyricDB(userlogged.getUser().getId(), musicIdTemp);			
	}
	
	
	public void saveLyric(){	
		le.upSaveLyricDB(userlogged.getUser().getId(), musicIdTemp, lyricTemp);			
	}
	
	public void updateLyric(){
	//	if(musicIdTemp!=0&&lyricEdited!="")
		le.updateLyricDB(userlogged.getUser().getId(), musicIdTemp, lyricEdited);
//		else
//			log.error("Update de letra cancelado: parâmetro nulo");
	}


	public void lyricString(){
//		if(musicIdTemp!=0)
		lyricTemp = le.readOriginalLyric(musicIdTemp);
//		else
//			log.error("Leitura de letra cancelada: parâmetro nulo");
	}

	public void lyricEditedString(){
	//	if(musicIdTemp!=0)
		lyricEdited=(le.readEditedLyric(userlogged.getUser().getId(),musicIdTemp));	
//		else
//			log.error("Leitura de letra cancelada: parâmetro nulo");
	}
	

	public String getLyricTemp(){
		lyricString(); 
		return lyricTemp;
	}

	public void setLyricTemp(String lyricTemp) {
		this.lyricTemp = lyricTemp;
	}

	public int getMusicIdTemp() {
		return musicIdTemp;
	}

	public void setMusicIdTemp(int musicIdTemp) {
		this.musicIdTemp = musicIdTemp;
	}

	public String getLyricEdited() {
		lyricEditedString();
		return lyricEdited;
	}

	public void setLyricEdited(String lyricEdited) {
		this.lyricEdited = lyricEdited;
	}


//	public boolean colorButton(){
//		
//		if (lyricTemp.equals(""))
//			return false;
//		
//		return true;
//	
//		
//	}


}
