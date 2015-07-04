package projecto4.grupo1.albertoricardo;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;



@RunWith(MockitoJUnitRunner.class)
public class SimpleMusicServiceTest {


	   
	    @Before
	    public void setUp() {
	        RestAssured.basePath = "/thews-ws/rest/Music";
	        RestAssured.baseURI = "http://localhost";
	        RestAssured.port = 8080;
	    }

	  
	   
	    @Test
	    public void userMusicsTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.XML).when().get("/List/1");
	    }
	   
	   
	    @Test
	    public void listMusicTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.XML).when().get("/ListMusic/1");
	    }
	   
}