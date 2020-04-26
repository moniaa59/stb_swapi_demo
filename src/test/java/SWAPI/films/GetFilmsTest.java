package SWAPI.films;

import SWAPI.common.CommonTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.db.type.DateValue;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetFilmsTest extends CommonTest {

    @Test
    public void readAllFilms() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + FILMS)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

//        System.out.println(json.getList("results.title"));

        List<String> titles = json.getList("results.title");

        System.out.println(titles.size());

        assertThat(7).isEqualTo(titles.size());
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

    }

    @Test
    public void readOneFilm() throws ParseException {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + FILMS + "/3")
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat("Return of the Jedi").isEqualTo(json.getString("title"));
        assertThat(6).isEqualTo(json.getInt("episode_id"));
        assertThat("Richard Marquand").isEqualTo(json.getString("director"));
        assertThat(json.getString("url")).isNotNull();
        assertThat(DateValue.of(1983, 5, 25)).isEqualByComparingTo(DateValue.parse(json.getString("release_date")));

    }

}
