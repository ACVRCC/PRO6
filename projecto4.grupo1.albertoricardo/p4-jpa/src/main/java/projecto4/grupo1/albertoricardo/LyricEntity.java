package projecto4.grupo1.albertoricardo;

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
	private MusicEntity musics;

	@ManyToOne
	private UserEntity userOwner;

	public LyricEntity() {
		super();
		this.lyric = lyric;
		this.musics = musics;
		this.userOwner = userOwner;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public MusicEntity getMusics() {
		return musics;
	}

	public void setMusics(MusicEntity musics) {
		this.musics = musics;
	}

	public UserEntity getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserEntity userOwner) {
		this.userOwner = userOwner;
	}
	@Override
	public String toString() {
		return "ID: "+id+", Musica: "+musics+", Letra: "+lyric;
	}
}
