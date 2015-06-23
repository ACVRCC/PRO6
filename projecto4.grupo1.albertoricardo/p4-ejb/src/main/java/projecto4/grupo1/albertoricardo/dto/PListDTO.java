package projecto4.grupo1.albertoricardo.dto;

import java.util.Date;
import java.util.List;

public class PListDTO {
	private Integer id;
	private String name;
	private Date insertDate;
	private List<MusicDTO>musics;
	private UserDTO userOwner;
	
	public PListDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PListDTO(Integer id, String name, Date insertDate,
			List<MusicDTO> musics, UserDTO userOwner) {
		super();
		this.id = id;
		this.name = name;
		this.insertDate = insertDate;
		this.musics = musics;
		this.userOwner = userOwner;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public List<MusicDTO> getMusics() {
		return musics;
	}
	public void setMusics(List<MusicDTO> musics) {
		this.musics = musics;
	}
	public UserDTO getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(UserDTO userOwner) {
		this.userOwner = userOwner;
	}
	
	
}
