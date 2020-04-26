package SWAPI.common;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;

public class CommonTest {

    protected final String BASE_URL = "https://swapi.py4e.com/api";
    protected final String FILMS = "/films";
    protected final String PLANETS = "/planets";
    protected final String VEHICLES = "/vehicles";
    protected final Integer SC_OK = HttpStatus.SC_OK;


    public static RequestSpecBuilder reqBuilder;
    public static RequestSpecification reqSpec;

    @BeforeAll
    public static void beforeAll() {

        reqBuilder = new RequestSpecBuilder();
        reqBuilder.addFilter(new AllureRestAssured());
        reqBuilder.setContentType(ContentType.JSON);

        reqSpec = reqBuilder.build();
    }
}
