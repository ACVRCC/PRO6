package projecto4.grupo1.albertoricardo.playlist;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projecto4.grupo1.albertoricardo.PlaylistEJBLocal;
import projecto4.grupo1.albertoricardo.PlaylistEntity;
import projecto4.grupo1.albertoricardo.user.UserLogged;



@Named
@ViewScoped
public class Playlist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(Playlist.class);


	@EJB
	private PlaylistEJBLocal playlistejb;
	@Inject
	private UserLogged userlogged;


	private String name;
	private Date insertDate;
	private int id;


	public Playlist(){

	}

	public Playlist(String name, Date insertDate){
		this.name=name;
		this.insertDate=insertDate;
	}

	
	
	
	public boolean verifyPlaylistName(){
		boolean found=false;
		List<PlaylistEntity> pl = playlistejb.getOwnPlaylists(userlogged.getUser().getId());
		log.info("lista do utilizador ", userlogged, " ok");
		for(PlaylistEntity p: pl)
			if(p.getName().equalsIgnoreCase(name)){
				found=true;
				log.info("nome de PL já existente");
			}
		return found;
	}


	public void insertPlaylist(){
		if(verifyPlaylistName()==false){
			Calendar now = Calendar.getInstance();
			insertDate = now.getTime();
			playlistejb.addPlaylist(name, insertDate, userlogged.getUser());
			log.info("Playlist criada pelo utilizador "+userlogged.getUser().getName());
			FacesMessage msg = new FacesMessage("Playlist","Playlist "+name+" criada com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			name ="";
		}
		else {
			log.info("Playlist com nome já existente");
			FacesMessage msg = new FacesMessage("Erro","Já existe uma playlist com o nome "+name);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public String updateNamePlaylist(){

		playlistejb.updateName(id, name);

		return "update name Playlist";		
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


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}











}
