package apiTests;

import io.restassured.specification.Argument;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class User {
    String uri = "https://petstore.swagger.io/v2/user/";
    int userId = 3737;
    String username = "Leogarcez";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }
    @Test(priority = 0)
    public void incluirUser() throws IOException {
        String jsonBody = lerJson("src/test/resources/data/user.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .body("code", is (200))
                .body("type", is ("unknown"))
                .body("message", is ("3737"))
        ;

    }
    @Test(priority = 1)
    public void consultarUser(){
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userId))
                .body("username", is("Leogarcez"))
                .body("firstName", is("Leonardo"))
                .body("lastName", is("Garcez"))
                .body("email", is("teste@iterasys.com.br"))
                .body("password", is("api2021"))
                .body("phone", is("969697070"))
                .body("userStatus", is(1))
        ;


    }
    @Test(priority = 2)
    public void alterarUser() throws IOException {
            String jsonBody = lerJson("src/test/resources/data/newuser.json");

            given()
                    .contentType("application/json")
                    .log().all()
                    .body(jsonBody) // Json a ser transmitido para a alteração
            .when()
                    .put(uri + username)
            .then()
                    .log().all()
                    .statusCode(200)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", is("3738"))
            ;


    }
    @Test(priority = 3)
    public void excluirUser(){

            given()
                    .contentType("application/json")
                    .log().all()
            .when()
                    .delete(uri + username)
            .then()
                    .log().all()
                    .statusCode(200)
                    .body("code", is(200))
                    .body("type", is ("unknown"))
                    .body("message", is(username))
            ;
    }
}
