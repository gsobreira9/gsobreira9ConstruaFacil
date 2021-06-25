package apiTests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Pet {
        String uri = "https://petstore.swagger.io/v2/pet";
        int petId = 1016;


    // Padrão
    // Given = Dado
    // .When = Quando
    // .Then = Então

    // Funções de Apoio
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test(priority = 0)
    public void incluirPet() throws IOException { // Create - Post

        String jsonBody = lerJson("src/test/resources/data/pet.json");

        given()                                                 //Dado
                .contentType("application/json")                //Tipo do conteúdo
                // "text/xml" para web services sincronos - ex: Correios
                // "application/json" para web services assincronos - ex: iFood
                .log().all()                                    //Registrar tudo do envio
                .body(jsonBody)
        .when()                                                 //Quando
                .post(uri)     //Comando + endpoint
        .then()                                                 // Então
                .log().all()                                    // Registrar tudo da volta
                .statusCode(200)                                // Valida o Código do Estado Nativo
                // .body("code", is(200))               // Valida o Código de Estado no Json
                .body("id", is(petId))        // Valida o id do animal
                .body("name", is("Thor"))            // Valida o nome do animal
                .body("category.name", is("dog"))       // Valida a categoria do animal
                //.body("tags.name", not(contains("não vermifugado")))  // Valida se contem a palavra chava
                .body("tags.id[0]", is(3))
                .body("tags.name[0]", stringContainsInOrder("primeira semana"))
                .body("tags.id[1]", is(4))
                .body("tags.name[1]", stringContainsInOrder("segunda semana"))
        ;
    }
    @Test (priority = 1)
    public void consultarPet(){

            given()
                    .contentType("application/json")
                    .log().all()
            .when()
                .get(uri + "/" + petId)

                .then()
                    .log().all()
                    .statusCode(200)
                    .body("name", is ("Thor"))
                    .body("status", is("available"))
            ;
    }
    @Test(priority = 2)  //dependsOnMethods = {"incluirPet"})
    public void alterarPet() throws IOException {
             String jsonBody = lerJson("src/test/resources/data/newpet.json");

            given()
                     .contentType("application/json")
                     .log().all()
                     .body(jsonBody) // Json a ser transmitido para a alteração
            .when()
                    .put(uri)//petstore.swagger.io/v2/pet/")

            .then()
                    .log().all()
                    .statusCode(200)
                    .body("status", is("sold"))
            ;


    }
    //@Test(priority = 3, dependsOnMethods = {"alterarPet"})
    @Test(priority = 3)
    public void excluirPet(){

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is (200))
                .body("type", is("unknown"))
                .body("message", is(Integer.toString(petId)))
        ;
    }
}