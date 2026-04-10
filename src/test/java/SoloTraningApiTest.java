import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@RunWith(Parameterized.class)
public class SoloTraningApiTest {

    private String baseURL = "https://jsonplaceholder.typicode.com";
    private RequestSpecification requestSpec;
    private int userId;
    private String expectedName , expectedCity , expectedEmail;


    // предустановка(переменная) для запросов.
    @Before
    public void setUp() {
        requestSpec = RestAssured
                .given()
                .baseUri(baseURL)
                .header("Content-Type", "application/json");
    }

    //параметры теста
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1, "Leanne Graham", "Sincere@april.biz" , "Gwenborough"},
                {2, "Ervin Howell", "Shanna@melissa.tv" , "Wisokyburgh"},
                {3, "Clementine Bauch", "Nathan@yesenia.net", "McKenziehaven"}
        });
    }

    public SoloTraningApiTest(int userId, String expectedName , String expectedEmail, String expectedCity) {
        this.userId = userId;
        this.expectedName = expectedName;
        this.expectedEmail = expectedEmail;
        this.expectedCity = expectedCity;
    }

    @Test
    //проверка схемы JSON
    public void testUserShema(){
        RestAssured
                .get(baseURL + "/users/" + userId)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }

    @Test
    public void taskOne() {
        Response response = RestAssured.get(baseURL + "/users/" + userId);

        //проверка статус код 200
        assertEquals("Status code not 200", 200, response.statusCode());
        //Проверка, что массив не пустой
        response.asString().isEmpty();
        // Проверка первого пользователя
        assertEquals(userId, response.jsonPath().getInt("id"));
        assertEquals(expectedName, response.jsonPath().getString("name"));
        assertEquals(expectedEmail, response.jsonPath().getString("email"));
        assertEquals(expectedCity, response.jsonPath().getString("address.city"));//город
        System.out.println("✅ Тест пройден.");
    }

    @Test
    public void taskThree() {
        Response response = RestAssured.get(baseURL + "/users/999");

        assertEquals("Status code not 404", 404, response.statusCode());//статус код
        System.out.println(response.jsonPath().getMap("").isEmpty() ? "Тело ответа пустое" : "Есть тело ответа");// проверка тела ответа
        System.out.println("✅ Тест пройден.");
    }

    @Test
    public void taskFour() {
        String rsBody = "{ \"title\": \"Мой первый пост\", \"body\": \"Текст поста\" , \"userId\": 1 }";

        Response response = requestSpec
                .body(rsBody)
                .post(baseURL + "/posts");

        assertEquals("Status code not 201", 201, response.statusCode());
        assertNotNull(response.jsonPath().getString("id"));
        assertEquals(1, response.jsonPath().getInt("userId"));
        assertEquals("Мой первый пост", response.jsonPath().getString("title"));
        assertEquals("Текст поста", response.jsonPath().getString("body"));
        System.out.println("✅ Тест пройден.");
    }

    @Test
    public void taskFive() {
        String rsBody = "{ \"id\": 1 , \"title\": \"Обновленный заголовок\", \"body\": \"Обновленный текст поста\" , \"userId\": 1 }";

        Response response = requestSpec
                .body(rsBody)
                .put(baseURL + "/posts/1");

        assertEquals("Status code not 200", 200, response.statusCode());
        assertEquals("Обновленный заголовок", response.jsonPath().getString("title"));
        assertEquals("Обновленный текст поста", response.jsonPath().getString("body"));
        System.out.println("✅ Тест пройден.");
    }

    @Test
    public void taskSix() {

        Response response = RestAssured
                .given()
                .delete(baseURL + "/posts/1");

        assertEquals(200, response.statusCode());
        System.out.println("✅ Тест пройден.");
    }
}