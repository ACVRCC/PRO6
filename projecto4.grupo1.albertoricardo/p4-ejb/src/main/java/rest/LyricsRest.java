package rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chartlyrics.api.Apiv1;
import com.chartlyrics.api.Apiv1Soap;


public class LyricsRest {
	
	private static Logger log = LoggerFactory.getLogger(LyricsRest.class);
	

	long start_time = System.currentTimeMillis();
	long wait_time = 2000;
	long end_time = start_time + wait_time;
	
	public String chartSoapLyrics(String song, String artist){
		String lyrics=null;
		int i=0;
		Apiv1 api = new Apiv1();
		Apiv1Soap soap = api.getApiv1Soap();
		while(i<10&& lyrics==null) {
			try{
	
				Thread.sleep(2000);
		lyrics = soap.searchLyricDirect(artist, song).getLyric();
		
			} catch (Exception e) {
				i++;
			log.info("Tentativa Soap em Chart da letra "+song+"nº :--> "+i);
		}
		}return lyrics;		
	}

	public String chartRestLyric(String song, String artist) {
		String lyrics=null;

		int i=0;
		
		while (true) {
			try{
				ResteasyClient cliente= new ResteasyClientBuilder().build();
				ResteasyWebTarget target=cliente.target("http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist="+artist+"&song="+song);
				Response response= target.request(MediaType.APPLICATION_XML).get();
				lyrics= response.readEntity(GetLyrics.class).getLyric();
				return lyrics;
			} catch(Exception e){
				i++;
				log.info("Tentativa Rest em Chart da letra "+song+"nº :--> "+i);
			}
		}
	}
	
	public String wikiaRestLyrics(String song, String artist){
		String lyrics=null;
		int i=0;
		while(i<11&& lyrics==null){
			try{
				ResteasyClient cliente= new ResteasyClientBuilder().build();
				ResteasyWebTarget target=cliente.target("http://lyrics.wikia.com/api.php?func=getSong&artist="+artist+"&song="+song+"&fmt=text");
				Response response= target.request(MediaType.APPLICATION_XML).get();
				lyrics= response.readEntity(String.class);
				return lyrics;
			} catch(Exception e){
				i++;
				log.info("Tentativa Rest em Wikia da letra "+song+"nº :--> "+i);

			}
		}
		return lyrics;
	}
	
	public String getLyric(String song, String artist) {
		String lyrics=null;

	
	while(System.currentTimeMillis() < end_time&& lyrics==null){
		log.info("Procura Soap em Chart ativa");
		lyrics = chartSoapLyrics(song, artist);	
		
	}
	while(System.currentTimeMillis() < (end_time+wait_time)&& lyrics==null){
		lyrics = wikiaRestLyrics(song, artist);	
		log.info("Procura Soap em Chart sem sucesso. Procura Rest em Wikia  da letra "+song+"ativa.");
	}
	while(lyrics==null){
		lyrics = chartRestLyric(song, artist);
		log.info("Procura Soap em Chart sem sucesso. Procura Rest em Wikia sem sucesso. Procura Rest em Chart da letra "+song);
	}
	return lyrics;
	
	}
}