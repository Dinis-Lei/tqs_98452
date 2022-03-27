package tqs.carservice;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarRestControllerIT {
    // will need to use the server port for the invocation url
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    public void whenValidInput_thenCreateCar() {
        Car test = new Car(1L, "Mustang", "Ford");
        restTemplate.postForEntity("/api/cars", test, Car.class);

        List<Car> found = repository.findAll();
        
        assertThat(found).extracting(Car::getCarId).containsOnly(test.getCarId());
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200()  {
        Car test1 = new Car("Ford", "Mustang");
        repository.saveAndFlush(test1);
        Car test2 = new Car("Toyota", "Corolla");
        repository.saveAndFlush(test2);
        

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getCarId).containsExactly(test1.getCarId(), test2.getCarId());

    }
}
