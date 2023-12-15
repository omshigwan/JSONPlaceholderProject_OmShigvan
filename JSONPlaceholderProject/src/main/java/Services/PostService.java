package Services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostService {

    static String BASE_URL = "https://jsonplaceholder.typicode.com";
    static String BASE_PATH = "/posts";
    RequestSpecification req_spec;

    public PostService() {
        req_spec = given().log().all().baseUri(BASE_URL).basePath(BASE_PATH);
    }

    public Response getAllPosts() {
        return given().spec(req_spec)
                .when().get();
    }

    public Response createPostByUserId(int userId) {
        return given().spec(req_spec).contentType(ContentType.JSON).body("{\n" +
                "    \"title\": \"my_first_post\",\n" +
                "    \"body\": \"have a nice day :)\",\n" +
                "    \"userId\": " + userId + " \n" +
                "}").when().post();
    }

    public Response getPostById(int Id) {
        return given().spec(req_spec)
                .when().get(String.valueOf(Id));
    }

    public Response updatePostById(int Id) {
        return given().spec(req_spec).contentType(ContentType.JSON).body("{\n" +
                "    \"id\": " + Id + " ,\n" +
                "    \"title\": \"message\",\n" +
                "    \"body\": \"Hello World!!!\",\n" +
                "    \"userId\": 1 \n" +
                "}").when().put(String.valueOf(Id));
    }

    public Response deletePostById(int Id) {
        return given().spec(req_spec)
                .when().delete(String.valueOf(Id));
    }

    public Response filterPostByUserId(int Id) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", String.valueOf(Id));
        return given().spec(req_spec).when().queryParams(queryParams).get(BASE_URL + BASE_PATH);
    }

    public Response getAllCommentsOfPostById(int Id) {
        return given().spec(req_spec)
                .when().get(Id + "/comments");
    }

}
