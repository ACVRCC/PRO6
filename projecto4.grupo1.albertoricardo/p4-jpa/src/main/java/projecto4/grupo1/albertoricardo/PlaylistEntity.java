package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table (name="playlists")
@XmlRootElement (name="playlists")
public class PlaylistEntity{

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,unique=true)
	private int id;
	@Column(nullable=false,unique=true)
	private String name;
	@Temporal(TemporalType.DATE)
	@Column(nullable=false,unique=false)
	private Date insertDate;
	@XmlTransient	
	@ManyToMany(fetch = FetchType.EAGER)
	List<MusicEntity>musics;
	@XmlTransient
	@ManyToOne
	private UserEntity userOwner;


	public PlaylistEntity() {

	}

	public PlaylistEntity(int id, String name, List<MusicEntity> musics) {
		this.id = id;
		this.name = name;
		this.musics = musics;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	} 
	
	@XmlTransient
	public List<MusicEntity> getMusics() {
		return musics;
	}


	public void setListMusic(List<MusicEntity> musics) {
		this.musics = musics;
	}


	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	@XmlTransient
	public UserEntity getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserEntity userOwner) {
		this.userOwner = userOwner;
	}

	public void setMusics(List<MusicEntity> musics) {
		this.musics = musics;
	}


}
