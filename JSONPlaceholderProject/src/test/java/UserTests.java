import Models.AlbumsPOJO;
import Models.PostPOJO;
import Models.TodoPOJO;
import Models.UserPOJO;
import Services.UserService;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static io.restassured.RestAssured.expect;

public class UserTests {
    UserService userServiceObj = new UserService();
    SoftAssert softAssert = new SoftAssert();

    static ResponseSpecification res_Spec;

    @BeforeClass
    public void specifications() {
        res_Spec = expect().statusCode(200);
    }

    @Test
    public void getAllUsers() {
        Response response = userServiceObj.getAllUsers()
                .then().log().all().spec(res_Spec).extract().response();
        UserPOJO[] userPOJO = response.as(UserPOJO[].class);

        for (int i = 0; i < Arrays.asList(userPOJO).size(); i++) {
            softAssert.assertEquals(userPOJO[i].id, i + 1);
            softAssert.assertNotNull(userPOJO[i].name);
            softAssert.assertNotNull(userPOJO[i].username);
            softAssert.assertNotNull(userPOJO[i].email);
            softAssert.assertNotNull(userPOJO[i].address);
            softAssert.assertNotNull(userPOJO[i].phone);
            softAssert.assertNotNull(userPOJO[i].company);
        }
        softAssert.assertAll();
    }

    @Test
    public void getUser() {
        Response response = userServiceObj.getUserById(1)
                .then().log().all().spec(res_Spec).extract().response();
        UserPOJO userPOJO = response.as(UserPOJO.class);

        softAssert.assertEquals(userPOJO.id, 1);
        softAssert.assertNotNull(userPOJO.name);
        softAssert.assertNotNull(userPOJO.username);
        softAssert.assertNotNull(userPOJO.email);
        softAssert.assertNotNull(userPOJO.address);
        softAssert.assertNotNull(userPOJO.phone);
        softAssert.assertNotNull(userPOJO.company);
        softAssert.assertAll();
    }

    @Test
    public void createUser() {
        Response response = userServiceObj.createUser()
                .then().log().all().extract().response();
        UserPOJO userPOJO = response.as(UserPOJO.class);

        softAssert.assertEquals(response.statusCode(), 201, "Status code should be 201: CREATED");
        softAssert.assertNotNull(String.valueOf(userPOJO.id));
        softAssert.assertEquals(userPOJO.name, "Om Shigvan");
        softAssert.assertEquals(userPOJO.username, "shigvanOm");
        softAssert.assertEquals(userPOJO.email, "om.shigvan@vodafone.com");
        softAssert.assertNotNull(userPOJO.address);
        softAssert.assertEquals(userPOJO.phone, "7083504552");
        softAssert.assertEquals(userPOJO.company.name, "_VOIS");
        softAssert.assertEquals(userPOJO.company.catchPhrase, "We move the world");
        softAssert.assertAll();
    }

    @Test
    public void deleteUser() {
        userServiceObj.deleteUserById(1)
                .then().spec(res_Spec);
    }

    @Test
    public void updateUser() {
        Response response = userServiceObj.updateUserById(1)
                .then().log().all().spec(res_Spec).extract().response();
        UserPOJO userPOJO = response.as(UserPOJO.class);

        softAssert.assertEquals(userPOJO.id, 1);
        softAssert.assertEquals(userPOJO.name, "Om Shigvan");
        softAssert.assertEquals(userPOJO.username, "shigvanOm");
        softAssert.assertEquals(userPOJO.email, "om.shigvan@vodafone.com");
        softAssert.assertNotNull(userPOJO.address);
        softAssert.assertEquals(userPOJO.phone, "7083504552");
        softAssert.assertEquals(userPOJO.company.name, "_VOIS");
        softAssert.assertEquals(userPOJO.company.catchPhrase, "We move the world");
        softAssert.assertAll();
    }

    @Test
    public void getAllUserPosts() {
        Response response = userServiceObj.getAllUserPosts(1)
                .then().log().all().spec(res_Spec).extract().response();
        PostPOJO[] postPOJO = response.as(PostPOJO[].class);

        for (int i = 0; i < Arrays.asList(postPOJO).size(); i++) {
            softAssert.assertEquals(postPOJO[i].userId, 1);
            softAssert.assertNotNull(String.valueOf(postPOJO[i].id));
            softAssert.assertNotNull(postPOJO[i].title);
            softAssert.assertNotNull(postPOJO[i].body);
        }
        softAssert.assertAll();
    }

    @Test
    public void getAllUserTodos() {
        Response response = userServiceObj.getAllUserTodos(1)
                .then().log().all().spec(res_Spec).extract().response();
        TodoPOJO[] todoPOJO = response.as(TodoPOJO[].class);

        for (int i = 0; i < Arrays.asList(todoPOJO).size(); i++) {
            softAssert.assertEquals(todoPOJO[i].userId, 1);
            softAssert.assertNotNull(String.valueOf(todoPOJO[i].id));
            softAssert.assertNotNull(todoPOJO[i].title);
            softAssert.assertNotNull(String.valueOf(todoPOJO[i].completed));
        }
        softAssert.assertAll();
    }

    @Test
    public void getAllUserAlbums() {
        Response response = userServiceObj.getAllUserAlbums(1)
                .then().log().all().spec(res_Spec).extract().response();
        AlbumsPOJO[] albumsPOJO = response.as(AlbumsPOJO[].class);

        for (int i = 0; i < Arrays.asList(albumsPOJO).size(); i++) {
            softAssert.assertEquals(albumsPOJO[i].userId, 1);
            softAssert.assertNotNull(String.valueOf(albumsPOJO[i].id));
            softAssert.assertNotNull(albumsPOJO[i].title);
        }
        softAssert.assertAll();
    }

}