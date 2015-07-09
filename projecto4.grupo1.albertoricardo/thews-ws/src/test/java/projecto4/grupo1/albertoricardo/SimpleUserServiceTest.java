package projecto4.grupo1.albertoricardo;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;



@RunWith(MockitoJUnitRunner.class)
public class SimpleUserServiceTest {


	   
	    @Before
	    public void setUp() {
	        RestAssured.basePath = "/thews-ws/rest/Utilizador";
	        RestAssured.baseURI = "http://localhost";
	        RestAssured.port = 8080;
	    }

	    @Test
	    public void countUsersTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.TEXT).when().get("/Count");
	    }
	   
	    @Test
	    public void findAllUsersTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.XML).when().get("/Find");
	    }
	    
	    @Test
	    public void userUsersTest() {
	        RestAssured.expect().statusCode(200).contentType(ContentType.XML).when().get("/List/alberto@mail.com");
	    }
	   

	   
}