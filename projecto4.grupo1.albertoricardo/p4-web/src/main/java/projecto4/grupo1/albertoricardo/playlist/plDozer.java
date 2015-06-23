package projecto4.grupo1.albertoricardo.playlist;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import projecto4.grupo1.albertoricardo.PlaylistEJBLocal;
import projecto4.grupo1.albertoricardo.dto.PListDTO;

@Named
@ViewScoped
public class plDozer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PlaylistEJBLocal plejb;
	
	private List<PListDTO> plist;
	
	public List<PListDTO> listPlaylists() {
		plist = plejb.getPlaylistDozer();
		return plist;
	}

	public List<PListDTO> getPlist() {
		return plist;
	}

	public void setPlist(List<PListDTO> plist) {
		this.plist = plist;
	}
	
	

}
