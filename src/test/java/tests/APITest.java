package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class APITest {

    @Test
    public void getUsersTest() {
        io.restassured.response.Response response = RestAssured
                .get("https://jsonplaceholder.typicode.com/posts");

        System.out.println("GET Response length: " + response.jsonPath().getList("").size());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void createUserTest() {
        String payload = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";
        io.restassured.response.Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post("https://jsonplaceholder.typicode.com/posts");

        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void updateUserTest() {
        String payload = "{\"id\": 1, \"title\": \"foo-updated\", \"body\": \"bar-updated\", \"userId\": 1}";
        io.restassured.response.Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .put("https://jsonplaceholder.typicode.com/posts/1");

        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo-updated");
    }

    @Test
    public void deleteUserTest() {
        io.restassured.response.Response response = RestAssured
                .delete("https://jsonplaceholder.typicode.com/posts/1");

        System.out.println("Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}