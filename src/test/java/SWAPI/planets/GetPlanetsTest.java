package SWAPI.planets;

import SWAPI.common.CommonTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPlanetsTest extends CommonTest {

    @Test
    public void readAllPlanets() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PLANETS)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

//        System.out.println(json.getList("results.name").size());

        List<String> planetNames = json.getList("results.name");

        assertThat(10).isEqualTo(planetNames.size());
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

    }

    @Test
    public void readOnePlanet() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PLANETS + "/10")
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat("Kamino").isEqualTo(json.getString("name"));
        assertThat(json.getInt("orbital_period")).isGreaterThan(json.getInt("rotation_period"));
        assertThat(json.getString("films")).contains("https:");
    }
}
