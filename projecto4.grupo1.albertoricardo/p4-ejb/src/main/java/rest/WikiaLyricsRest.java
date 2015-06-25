package rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class WikiaLyricsRest {


	public static void main(String[] args) {
		int i=0;
		while(true){
			try{
				String song="You're My Heart, You're My Soul";
				String artist="Modern Talking";
				ResteasyClient cliente= new ResteasyClientBuilder().build();
				ResteasyWebTarget target=cliente.target("http://lyrics.wikia.com/api.php?func=getSong&artist="+artist+"&song="+song+"&fmt=text");
				Response response= target.request(MediaType.APPLICATION_XML).get();
				String lyrics= response.readEntity(String.class);
				System.out.println(lyrics);
				return;
			} catch(Exception e){
				i++;
				System.out.println("Tentativa nº :--> "+i);

			}
		}
	}


}