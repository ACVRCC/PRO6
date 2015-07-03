package projecto4.grupo1.albertoricardo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lyrics")
public class LyricEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "lyric", nullable = true, length = 999999) //length 999999 porque por defeito varChar vem com 256 caracteres insuficiente para a letra de uma musica normal
	private String lyric;

	@ManyToOne
	private MusicEntity music;

	@ManyToOne
	private UserEntity userOwner;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LyricEntity(){
		
	}

	public LyricEntity(int id, String lyric) {
		this.id = id;
		this.lyric = lyric;
	
	}



	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}


	public MusicEntity getMusic() {
		return music;
	}

	public void setMusic(MusicEntity music) {
		this.music = music;
	}

	public UserEntity getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserEntity userOwner) {
		this.userOwner = userOwner;
	}
	@Override
	public String toString() {
		return "ID: "+id+", Musica: "+music+", Letra: "+lyric;
	}
}
