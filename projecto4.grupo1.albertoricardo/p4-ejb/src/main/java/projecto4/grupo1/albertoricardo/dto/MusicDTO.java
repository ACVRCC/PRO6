package projecto4.grupo1.albertoricardo.dto;

import java.util.Date;
import java.util.List;

public class MusicDTO {
	private Integer id;
	private String title;
	private String artist;
	private String album;
	private Date dateRecord;
	private String pathFile;
	private List<PListDTO> playlists;
	private UserDTO userOwner;
	private LyricDTO lyric;
	
	public LyricDTO getLyric() {
		return lyric;
	}

	public void setLyric(LyricDTO lyric) {
		this.lyric = lyric;
	}

	public Integer getId() {
		return id;
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
	public Date getDateRecord() {
		return dateRecord;
	}
	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}
	public String getPathFile() {
		return pathFile;
	}
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	public List<PListDTO> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(List<PListDTO> playlists) {
		this.playlists = playlists;
	}
	public UserDTO getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(UserDTO userOwner) {
		this.userOwner = userOwner;
	}
	
	
	

}
