package rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class LyricsRest {


	public static void main(String[] args) {
		int i=0;
		while(true){
			try{
				String song="Chiquitita";
				String artist="Abba";
				ResteasyClient cliente= new ResteasyClientBuilder().build();
				ResteasyWebTarget target=cliente.target("http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist="+artist+"&song="+song);
				Response response= target.request(MediaType.APPLICATION_XML).get();
				String lyrics= response.readEntity(GetLyrics.class).getLyric();
				System.out.println(lyrics);
				return;
			} catch(Exception e){
				i++;
				System.out.println("Tentativa nº :--> "+i);

			}
		}
	}


}