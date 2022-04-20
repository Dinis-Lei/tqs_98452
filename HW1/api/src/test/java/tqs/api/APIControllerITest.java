package tqs.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.anyDouble;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class APIControllerITest {
    
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getDataByCountryTest(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate yesyesterday = today.minusDays(2);

        ResponseEntity<ArrayList<CovidData>> response = restTemplate
            .exchange("/api/portugal", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<CovidData>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
            .extracting(CovidData::getCountry, CovidData::getDay)
            .contains(
                tuple("Portugal", today.toString()),
                tuple("Portugal", yesterday.toString()),
                tuple("Portugal", yesyesterday.toString())
            );
        
            ResponseEntity<ArrayList<CovidData>> response2 = restTemplate
                .exchange("/api/usa?numOfDays=2", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<CovidData>>() {});

            assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response2.getBody())
                .extracting(CovidData::getCountry, CovidData::getDay)
                .contains(
                    tuple("USA", today.toString()),
                    tuple("USA", yesterday.toString())
            );
    }

    @Test
    void getCacheStatsTest(){
        ResponseEntity<HashMap<String, Double>> response = restTemplate
            .exchange("/api/cachestats", HttpMethod.GET, null, new ParameterizedTypeReference<HashMap<String,Double>>() {});
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
            .extracting("hits", "miss", "total_requests", "ratio")
            .contains(
                anyDouble(),anyDouble(),anyDouble(),anyDouble()
        );
        
        }

}
