package tqs;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;


@WebMvcTest(CarController.class)
public class RestAssuredTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        Car mustang = new Car("Ford", "Mustang");
        when(service.save(Mockito.any())).thenReturn(mustang);
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(JsonUtils.toJson(mustang))
                .when()
                .post("/api/cars")
                .then()
                .statusCode(201)
                .and().body("maker", equalTo(mustang.getMaker()))
                .and().body("model", equalTo(mustang.getModel()))
        ;
        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() {
        Car testCar1 = new Car("Ford", "Mustang");
        Car testCar2 = new Car("Renault", "Clio");
        Car testCar3 = new Car("Toyota", "Corolla");

        List<Car> allCars = Arrays.asList(testCar1, testCar2, testCar3);

        when( service.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .get("/api/cars")
                .then()
                .statusCode(200)
                .body("", hasSize(3))
                .and().body("[0].maker", equalTo(testCar1.getMaker()))
                .and().body("[0].model", equalTo(testCar1.getModel()))
                .and().body("[1].maker", equalTo(testCar2.getMaker()))
                .and().body("[1].model", equalTo(testCar2.getModel()))
                .and().body("[2].maker", equalTo(testCar3.getMaker()))
                .and().body("[2].model", equalTo(testCar3.getModel()))
        ;
        verify(service, times(1)).getAllCars();
    }

    @Test
    void testGetCarById() {
        Car testCar = new Car(1L, "Ford", "Mustang");
        Optional<Car> test = Optional.of(testCar);

        when(  service.getCarDetails(testCar.getCarId())).thenReturn( test);

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .get("/api/cars/1")
                .then()
                .statusCode(200)
                .body("maker", equalTo(testCar.getMaker()))
                .and().body("model", equalTo(testCar.getModel()))
        ;



        verify(service, times(1)).getCarDetails(1L);
    }
}

