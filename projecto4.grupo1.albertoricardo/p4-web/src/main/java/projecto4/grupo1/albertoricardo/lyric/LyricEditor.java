package projecto4.grupo1.albertoricardo.lyric;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.LyricEditorEJB;
import projecto4.grupo1.albertoricardo.user.UserLogged;

@Named
@RequestScoped
public class LyricEditor implements Serializable{
	

	private static final long serialVersionUID = 1L;
	

		@EJB
		private LyricEditorEJB le;
		@Inject
		private UserLogged userlogged;
		
		private String lyricTemp="";
		private int musicIdTemp=0;
	

		@SuppressWarnings("unused")
		private static Logger log = LoggerFactory.getLogger(LyricEditor.class);
		
		
		public void uploadLyric(int idMusic){
			le.uploadLyricDB(userlogged.getUser().getId(), idMusic);
			System.out.println("get Lyric");
			
		}
		
		public void lyricString(int idMusic){
			setLyricTemp(le.readLyric(userlogged.getUser().getId(), idMusic));
			System.out.println(lyricTemp);
			
		}
		
		public void lyricString2(){
			setLyricTemp(le.readLyric(userlogged.getUser().getId(), musicIdTemp));
			System.out.println("music id" + musicIdTemp);
			System.out.println("lyric temp" +lyricTemp);
			
		}
		
		
		
		public String getLyricTemp(){
			System.out.println("getLyricTemp");
			lyricString2(); 
			return lyricTemp;
			
		}

		public void setLyricTemp(String lyricTemp) {
			System.out.println("setLyricTemp");
			this.lyricTemp = lyricTemp;
		}

		public int getMusicIdTemp() {
			return musicIdTemp;
		}

		public void setMusicIdTemp(int musicIdTemp) {
			System.out.println("set music id" + musicIdTemp);
			this.musicIdTemp = musicIdTemp;
		}
	

	

}
