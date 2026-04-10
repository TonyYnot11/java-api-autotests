import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FirstApiTest {

    @Test
    public void testGetUser1() {
        //mini task 1
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/");
        System.out.println(response.asString());}

    @Test
    public void testGetUser() {
        //mini task 1
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/2");
        assertEquals("Статус код не 200", 200, response.statusCode());
        assertEquals("Ervin Howell", response.jsonPath().getString("name"));
        assertEquals("Shanna@melissa.tv", response.jsonPath().getString("email"));
        System.out.println("✅ Тест пройден.");
    }

    //mini task 2
    @Test
    public void testUserCityById() {

        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/1");
        assertEquals("Gwenborough", response.jsonPath().getString("address.city"));
        System.out.println("✅ Тест пройден.");
    }

    //mini task 3
    @Test
    public void userInfo(){
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/3");
        assertEquals("Статус код не 200", 200, response.statusCode());
        assertEquals("Clementine Bauch", response.jsonPath().getString("name"));
        assertEquals("Romaguera-Jacobson" , response.jsonPath().getString("company.name"));
        assertEquals("1-463-123-4447", response.jsonPath().getString("phone"));
        System.out.println("✅ Тест пройден.");
    }

    @Test
    public void negativeStatus(){
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/898");
        assertEquals("Статус код не 404", 404, response.statusCode());
        System.out.println("✅ Тест пройден. Статус код - " + response.statusCode());
    }
}
