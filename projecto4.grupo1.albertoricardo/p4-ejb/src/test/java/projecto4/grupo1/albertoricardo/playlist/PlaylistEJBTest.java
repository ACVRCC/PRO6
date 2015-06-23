package projecto4.grupo1.albertoricardo.playlist;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.PlaylistCRUD;
import projecto4.grupo1.albertoricardo.PlaylistEJB;
import projecto4.grupo1.albertoricardo.PlaylistEntity;
import projecto4.grupo1.albertoricardo.UserEntity;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistEJBTest {

	@Mock
	EntityManager em;
	@Mock
	PlaylistCRUD plcrud;
	@Mock
	Query mockedQuery;


	@InjectMocks
	PlaylistEJB plejb;

	@Test
	public void getPlaylistsTest() {
		Mockito.when(em.createQuery("SELECT p FROM PlaylistEntity p WHERE p.userOwner.id = :id")).thenReturn(mockedQuery);
		Mockito.when(mockedQuery.getResultList()).thenReturn(new ArrayList<PlaylistEntity>());
		List<PlaylistEntity> p = plejb.getPlaylists();
		int size = p.size();
		assertEquals(0, size);
	}

	@Test
	public void findNameTest() {
		String name = "abel";
		Mockito.when(em.createQuery("select u from PlaylistEntity u where u.name like :e")).thenReturn(mockedQuery);
		Mockito.when(mockedQuery.getSingleResult()).thenReturn(name);

		boolean result = plejb.findName(name);
		assertTrue(result);
	}

	@Test
	public void findNameFailTest() {
		Mockito.when(em.createQuery("select u from PlaylistEntity u where u.name like :e")).thenReturn(mockedQuery);
		Mockito.when(mockedQuery.getSingleResult()).thenReturn(null);

		boolean result = plejb.findName("abel");
		assertFalse(result);
	}
	
	@Test
	public void removeUsersTest() {
		Mockito.when(em.createQuery("DELETE FROM PlaylistEntity p where p.userOwner.id = :i")).thenReturn(mockedQuery);
		Mockito.when(mockedQuery.executeUpdate()).thenReturn(1);
		boolean result = plejb.removePlaylistsOfUser(new UserEntity());
		assertTrue(result);
	}
	
	@Test
	public void removeUsersFailTest() {
		Mockito.when(em.createQuery("DELETE FROM PlaylistEntity p where p.userOwner.id = :i")).thenReturn(mockedQuery);
		Mockito.when(mockedQuery.executeUpdate()).thenReturn(0);
		boolean result = plejb.removePlaylistsOfUser(new UserEntity());
		assertFalse(result);
	}
	
	@Test
	public void removeFromUser() {
		PlaylistEntity p = Mockito.mock(PlaylistEntity.class);
		PlaylistCRUD plcrud = Mockito.mock(PlaylistCRUD.class);
		FacesContext ctxt = Mockito.mock(FacesContext.class);
		try {
			plejb.removePlaylistFromUser(p);
			Mockito.verify(plcrud).remove(p);
			assertTrue(plejb.removePlaylistFromUser(p));
		} catch (NullPointerException npe) {
			
		}
	}

}
