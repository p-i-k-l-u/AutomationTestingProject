package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.dockerjava.transport.DockerHttpClient.Response;

import io.restassured.RestAssured;

public class APITest {

    @Test
    public void getUsersTest() {

        io.restassured.response.Response response =

            RestAssured
            .get(
            "https://reqres.in/api/users?page=2"
            );

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );
    }
}