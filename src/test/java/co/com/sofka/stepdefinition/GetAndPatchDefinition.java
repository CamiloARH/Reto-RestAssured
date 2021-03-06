package co.com.sofka.stepdefinition;

import co.com.sofka.runner.RetoCucumberRestAssured;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetAndPatchDefinition extends Reqresin{

    private Response response;
    private static final Logger LOGGER = Logger.getLogger(RetoCucumberRestAssured.class);
    //Scenario 1
    @Given("^el usuario esta en la pagina de busqueda y desea buscar en la lista de recursos$")
    public void elUsuarioEstaEnLaPaginaDeBusquedaYDeseaBuscarEnLaListaDeRecursos() {
        try {
            generalSteUp();
            LOGGER.info("Se organiza el setUp inicial");
        } catch (Exception e) {
            LOGGER.error("Problema en el Give");
            Assertions.fail(e.getMessage());
        }
    }

    @When("^el usuario busca el recurso que desea y obtiene una respuesta (.+)$")
    public void elUsuarioBuscaElRecursoQueDeseaYObtieneUnaRespuesta(String respuesta){
        try {
            if(respuesta.equals("HttpStatus.SC_OK")){
                response = given()
                        .get(RESOURCE_UNKNOWN);
            }else if(respuesta.equals("HttpStatus.SC_NOT_FOUND")){
                response = given()
                        .get(RESOURCE_UNKNOWN_NOT_FOUND);
            }else{
                LOGGER.info("No es ninguna de las esperadas");
            }
        } catch (Exception e) {
            LOGGER.error("Problema en el when");
            Assertions.fail(e.getMessage());
        }
    }

    @Then("^el usuario debera ver la data del recurso como su nombre (.+) o no ver nada si el recurso no existe$")
    public void elUsuarioDeberaVerLaDataDelRecursoComoSuNombreONoVerNadaSiElRecursoNoExiste(String name){
        try {
            if(name.equals("fuchsia rose")){
                response.then().statusCode(HttpStatus.SC_OK).body("data",notNullValue());
            }else if(name.equals("null")){
                response.then().statusCode(HttpStatus.SC_NOT_FOUND);
            }else {
                LOGGER.info("No es ninguna de las esperadas");
            }

        } catch (Exception e) {
            LOGGER.error("Problema en el then");
            Assertions.fail(e.getMessage());
        }
    }

    // Scenario 2
    @Given("el usuario esta en la pagina de update de usuarios")
    public void elUsuarioEstaEnLaPaginaDeUpdateDeUsuarios() {
        try {
            generalSteUp();
            LOGGER.info("Se organiza el setUp inicial");
        } catch (Exception e) {
            LOGGER.error("Problema en el Give");
            Assertions.fail(e.getMessage());
        }
    }

    @When("el usuario escribe el nombre {string} y cambia su cargo {string}")
    public void elUsuarioEscribeElNombreYCambiaSuCargo(String name, String job) {
        try {
            RequestSpecification request = given().body("{\n" +
                    "    \"name\": \"" + name + "\",\n" +
                    "    \"job\": \"" + job + "\"\n" +
                    "}");
            response = request
                    .patch(RESOURCE_USERS);

        } catch (Exception e) {
            LOGGER.error("Problema en el when");
            Assertions.fail(e.getMessage());
        }
    }

    @Then("el usuario obtiene una respuesta y ve sus nuevos datos")
    public void elUsuarioObtieneUnaRespuestaYVeSusNuevosDatos() {
        try {
            response
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("updatedAt", notNullValue());

        } catch (Exception e) {
            LOGGER.error("Problema en el then");
            Assertions.fail(e.getMessage());
        }
    }
}
