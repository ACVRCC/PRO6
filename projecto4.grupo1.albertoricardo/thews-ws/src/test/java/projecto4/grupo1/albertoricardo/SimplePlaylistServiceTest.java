package projecto4.grupo1.albertoricardo;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;



@RunWith(MockitoJUnitRunner.class)
public class SimplePlaylistServiceTest {


	   
	    @Before
	    public void setUp() {
	        RestAssured.basePath = "/thews-ws/rest/Playlist";
	        RestAssured.baseURI = "http://localhost";
	        RestAssured.port = 8080;
	    }
	    
	    @Test
	    public void countPlaylistsTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.TEXT).when().get("/Count");
	    }
	  
	    @Test
	    public void findAllPlaylistsTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.XML).when().get("/Find");
	    }
	  
	    @Test
	    public void userPlaylistsTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.XML).when().get("/List/1");
	    }
	   
	   
	    @Test
	    public void listPlaylistTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.XML).when().get("/ListMusic/1");
	    }
	   
}