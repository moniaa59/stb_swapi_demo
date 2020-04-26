package SWAPI.vehicles;

import SWAPI.common.CommonTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetVehiclesTest extends CommonTest {

    @Test
    public void readAllVehicles() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + VEHICLES)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

//        System.out.println(json.getList("results.name").size());

        List<String> vehicleNames = json.getList("results.name");

        assertThat(10).isEqualTo(vehicleNames.size());
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

    }

    @Test
    public void readOneVehicle() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + VEHICLES + "/4")
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<Array> filmsUrl = json.getList("films");

        assertThat(2).isEqualTo(filmsUrl.size());
        assertThat("Sand Crawler").isEqualTo(json.getString("name"));
        assertThat("Digger Crawler").isEqualTo(json.getString("model"));
        assertThat(json.getString("edited")).isNotEqualTo(json.getString("created"));

    }

}
