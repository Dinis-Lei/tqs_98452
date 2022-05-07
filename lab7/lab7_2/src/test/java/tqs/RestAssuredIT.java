package tqs;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CarserviceApplication.class)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create")
@AutoConfigureMockMvc
class RestAssuredIT {
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
            .withUsername("user")
            .withPassword("user")
            .withDatabaseName("cars");

    @LocalServerPort
    int testPort;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    private Car test1; 
    private Car test2; 
    private Car test3;

    @Autowired
    private CarRepository repository;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
        test1 = repository.save(new Car("Ford", "Mustang"));
        test2 = repository.save(new Car("Renaut", "Clio"));
        test3 = new Car("Toyota", "Corolla");
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(JsonUtils.toJson(test3))
                .when()
                .post("/api/cars")
                .then()
                .statusCode(201)
                .and().body("maker", equalTo(test3.getMaker()))
                .and().body("model", equalTo(test3.getModel()))
        ;
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .get("/api/cars")
                .then()
                .statusCode(200)
                .body("", hasSize(2))
                .and().body("[0].maker", equalTo(test1.getMaker()))
                .and().body("[0].model", equalTo(test1.getModel()))
                .and().body("[1].maker", equalTo(test2.getMaker()))
                .and().body("[1].model", equalTo(test2.getModel()))
        ;
    }

    @Test
    void testGetCarById() {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .get("/api/cars/" + test1.getCarId())
                .then()
                .body("maker", equalTo(test1.getMaker()))
                .and().body("model", equalTo(test1.getModel()))
        ;
    }
}
