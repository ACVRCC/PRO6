package projecto4.grupo1.albertoricardo.music;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.MusicEJB;
import projecto4.grupo1.albertoricardo.MusicListEJB;

@RunWith(MockitoJUnitRunner.class)
public class MusicListEJBTest {
	@Mock
	EntityManager em;
	@Mock
	MusicEJB musicejb;
	@Mock
	Query mockedQuery;
	@InjectMocks
	MusicListEJB mlistejb;

	@Test
	public void listMusicsTest() {
		Mockito.when(em.createQuery("select m from MusicEntity m")).thenReturn(mockedQuery);
		Mockito.when(mockedQuery.getResultList()).thenReturn(new ArrayList<>());
		assertNotNull(mlistejb.listMusics());
	}
	
	@Test
	public void listMusicsFailTest() {
		Mockito.when(em.createQuery("select m from MusicEntity m")).thenReturn(mockedQuery);
		Mockito.when(mockedQuery.getResultList()).thenReturn(null);
		assertNull(mlistejb.listMusics());
	}
	

}
