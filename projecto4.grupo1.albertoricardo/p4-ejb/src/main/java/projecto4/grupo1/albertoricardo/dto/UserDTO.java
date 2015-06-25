package projecto4.grupo1.albertoricardo.dto;

import java.util.List;

public class UserDTO {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private List<MusicDTO> uploadedMusics;
	private List<PListDTO> userPlaylists;
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<MusicDTO> getUploadedMusics() {
		return uploadedMusics;
	}
	public void setUploadedMusics(List<MusicDTO> uploadedMusics) {
		this.uploadedMusics = uploadedMusics;
	}
	public List<PListDTO> getUserPlaylists() {
		return userPlaylists;
	}
	public void setUserPlaylists(List<PListDTO> userPlaylists) {
		this.userPlaylists = userPlaylists;
	}
	
	

}
