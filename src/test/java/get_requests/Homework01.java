package get_requests;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;


public class Homework01 extends AutomationExerciseBaseUrl {
    /*
Given
 https://automationexercise.com/api/productsList
When
 User sends a GET Request to the url
Then
 HTTP Status Code should be 200
And
 Content Type should be "text/html; charset=utf-8"
And
 Status Line should be HTTP/1.1 200 OK
And
  There must be 12 Women, 9 Men, 13 Kids usertype in products


  */
    @Test
    public void h01() {
        //Set the Url
        spec.pathParam("first","productsList");
        //Send The Request and Get The Response
        Response response = given().spec(spec).get("/{first}");
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();
        //Do Assertion
        //Then
        //    HTTP Status Code should be 200
        assertEquals(200,response.statusCode());
        //And
        //    Content Type should be "text/html; charset=utf-8"
        assertEquals("text/html; charset=utf-8",response.getContentType());
        //And
        //    Status Line should be HTTP/1.1 200 OK
        assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        //And
        //     There must be 12 Women, 9 Men, 13 Kids usertype in products
        List<String> categories =  jsonPath.getList("products.category.usertype.usertype");
        int countWomen = 0;
        int countMen = 0;
        int countKids = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals("Women")) countWomen++;
            if (categories.get(i).equals("Men")) countMen++;
            if (categories.get(i).equals("Kids")) countKids++;
        }
        System.out.println("countWomen = " + countWomen);
        System.out.println("countMen = " + countMen);
        System.out.println("countKids = " + countKids);
        assertEquals(12,countWomen);
        assertEquals(9,countMen);
        assertEquals(13,countKids);

    }
}