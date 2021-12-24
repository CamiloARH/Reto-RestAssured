package co.com.sofka.stepdefinition;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

public class Reqresin {
    private static final String BASE_URL = "https://reqres.in/";
    private static final String BASE_PATH = "api/";
    protected static final String RESOURCE_USERS = "users/2";
    protected static final String RESOURCE_UNKNOWN = "unknown/2";
    protected static final String RESOURCE_UNKNOWN_NOT_FOUND = "unknown/23";

    protected void generalSteUp(){
        configurationForRestAssured();
    }
    private void configurationForRestAssured(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
}
