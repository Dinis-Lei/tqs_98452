package tqs;

import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredTest {

    private static final String URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void checkConnectivity() {
        when().get( URL + "/todos").then().statusCode(200);
    }

    @Test
    public void checkToDosNumber4() {
        when().get(URL + "/todos/4").then().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void checkToDosId198and199() {
        when().get(URL + "/todos").then().body("id", hasItems(198, 199));
    }

}
