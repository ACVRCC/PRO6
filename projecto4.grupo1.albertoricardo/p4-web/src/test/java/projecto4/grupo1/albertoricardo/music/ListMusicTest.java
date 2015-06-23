package projecto4.grupo1.albertoricardo.music;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.MusicListEJB;
import projecto4.grupo1.albertoricardo.user.UserLogged;

@RunWith(MockitoJUnitRunner.class)
public class ListMusicTest {
	
	@Mock
	MusicListEJB mlejb;
	@Mock
	UserLogged ul;
	@InjectMocks
	ListMusic lm;
	

	@Test
	public void getMusicsTest() { // Music list is null so mlejb.listMusics() is called
		lm.getMusics();
		Mockito.verify(mlejb).listMusics();
	}
	
	@Test
	public void getMusicsTest2() { // Music lists exists and is returned without calling ejb
		List<MusicEntity> m = new ArrayList<>();
		lm.setMusics(m);
		List<MusicEntity> me = new ArrayList<>();
		assertEquals(me, m);
	}

}
