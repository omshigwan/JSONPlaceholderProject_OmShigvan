package Services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserService {
    static String BASE_URL = "https://jsonplaceholder.typicode.com";
    static String BASE_PATH = "/users";
    RequestSpecification req_spec;

    public UserService() {
        req_spec = given().log().all().baseUri(BASE_URL).basePath(BASE_PATH);
    }

    public Response getAllUsers() {
        return given().spec(req_spec)
                .when().get();
    }

    public Response getUserById(int Id) {
        return given().spec(req_spec)
                .when().get(String.valueOf(Id));
    }

    public Response createUser() {
        return given().spec(req_spec).contentType(ContentType.JSON).body("""
                        {
                            "name": "Om Shigvan",
                            "username": "shigvanOm",
                            "email": "om.shigvan@vodafone.com",
                            "address": {
                                "street": "Kulas Light",
                                "suite": "Apt. 556",
                                "city": "Gwenborough",
                                "zipcode": "92998-3874",
                                "geo": {
                                    "lat": "-37.3159",
                                    "lng": "81.1496"
                                }
                            },
                            "phone": "7083504552",
                            "website": "hildegard.org",
                            "company": {
                                "name": "_VOIS",
                                "catchPhrase": "We move the world",
                                "bs": "harness real-time e-markets"
                            }
                        }""")
                .when().post();
    }

    public Response deleteUserById(int Id) {
        return given().spec(req_spec)
                .when().delete(String.valueOf(Id));
    }

    public Response updateUserById(int Id) {
        return given().spec(req_spec).contentType(ContentType.JSON).body("{\n" +
                        "    \"id\": " + Id + ",\n" +
                        "    \"name\": \"Om Shigvan\",\n" +
                        "    \"username\": \"shigvanOm\",\n" +
                        "    \"email\": \"om.shigvan@vodafone.com\",\n" +
                        "    \"address\": {\n" +
                        "        \"street\": \"Kulas Light\",\n" +
                        "        \"suite\": \"Apt. 556\",\n" +
                        "        \"city\": \"Gwenborough\",\n" +
                        "        \"zipcode\": \"92998-3874\",\n" +
                        "        \"geo\": {\n" +
                        "            \"lat\": \"-37.3159\",\n" +
                        "            \"lng\": \"81.1496\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"phone\": \"7083504552\",\n" +
                        "    \"website\": \"hildegard.org\",\n" +
                        "    \"company\": {\n" +
                        "        \"name\": \"_VOIS\",\n" +
                        "        \"catchPhrase\": \"We move the world\",\n" +
                        "        \"bs\": \"harness real-time e-markets\"\n" +
                        "    }\n" +
                        "}")
                .when().put(String.valueOf(Id));
    }

    public Response getAllUserPosts(int Id) {

        return given().spec(req_spec)
                .when().get(Id + "/posts");
    }

    public Response getAllUserTodos(int Id) {

        return given().spec(req_spec)
                .when().get(Id + "/todos");
    }

    public Response getAllUserAlbums(int Id) {
        return given().spec(req_spec)
                .when().get(Id + "/albums");
    }

}
