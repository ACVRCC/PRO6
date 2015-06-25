package projecto4.grupo1.albertoricardo.dto;

import java.util.List;

public class LyricDTO {
	private int Id;
	private UserDTO userOwner;
	private List<MusicDTO> uploadedMusics;
	
	
	public UserDTO getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(UserDTO userOwner) {
		this.userOwner = userOwner;
	}
	public int getId() {
		return Id;
	}
	public List<MusicDTO> getUploadedMusics() {
		return uploadedMusics;
	}
	public void setUploadedMusics(List<MusicDTO> uploadedMusics) {
		this.uploadedMusics = uploadedMusics;
	}
}
