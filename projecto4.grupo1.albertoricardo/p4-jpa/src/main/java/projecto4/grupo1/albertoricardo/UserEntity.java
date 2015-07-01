package projecto4.grupo1.albertoricardo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table (name="users")
@XmlRootElement (name="user")
public class UserEntity {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(unique=true,nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String name;
	@XmlTransient
	@OneToMany(mappedBy="userOwner")
	private List<MusicEntity> uploadedMusics;
	@XmlTransient
	@OneToMany(cascade =CascadeType.REMOVE,mappedBy="userOwner")
	private List<PlaylistEntity> userPlaylists;
	@Column(name="role", nullable=false)
	private String role;
	

	public UserEntity() {
		super();
		this.role="CLIENT";
	}
	public UserEntity(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.role="CLIENT";
	}

	public String getRole() {
		return role;
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



	@Override
	public String toString() {
		return "ID: "+id+", E-mail: "+email+", password: "+password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
