package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name="musics")
public class MusicEntity {
	

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,unique=true)
	private int id;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String artist;
	@Column(nullable=false)
	private String album;
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dateRecord;
	@Column(nullable=false)
	private String pathFile;
	@ManyToMany(mappedBy="musics")
	List<PlaylistEntity> playlists;
	@ManyToOne
	private UserEntity userOwner;
//	@OneToMany(cascade =CascadeType.REMOVE,mappedBy="userOwner")
//	private List<LyricEntity> lyricentities;

	
//	static Logger logger = LoggerFactory.getLogger(MusicEntity.class);
	
	public MusicEntity() {

	}

	public MusicEntity(int id, String title, String artist, String album, Date date,
			String pathFile) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.dateRecord=date;
		this.pathFile = pathFile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public Date getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	public UserEntity getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserEntity userOwner) {
		this.userOwner = userOwner;
	}

	
 
	
	
	
	
}