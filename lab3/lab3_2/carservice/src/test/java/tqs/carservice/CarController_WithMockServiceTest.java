package tqs.carservice;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CarController.class)
public class CarController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    // inject required beans as "mockeable" objects
    // note that @AutoWire would result in NoSuchBeanDefinitionException
    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setup(){}

    @Test
    public void whenPostCar_thenCreateCar() throws IOException, Exception{
        Car testCar = new Car("Ford", "Mustang");

        when(service.save(Mockito.any())).thenReturn(testCar);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(testCar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Ford")))
                .andExpect(jsonPath("$.model", is("Mustang")));
    }

    @Test
    public void whenGetCars_thenReturnJsonArray() throws Exception{
        Car testCar1 = new Car("Ford", "Mustang");
        Car testCar2 = new Car("Renault", "Clio");
        Car testCar3 = new Car("Toyota", "Corolla");


        List<Car> allCars = Arrays.asList(testCar1, testCar2, testCar3);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(testCar1.getMaker())))
                .andExpect(jsonPath("$[0].model", is(testCar1.getModel())))
                .andExpect(jsonPath("$[1].maker", is(testCar2.getMaker())))
                .andExpect(jsonPath("$[1].model", is(testCar2.getModel())))
                .andExpect(jsonPath("$[2].maker", is(testCar3.getMaker())))
                .andExpect(jsonPath("$[2].model", is(testCar3.getModel())));
        verify(service, times(1)).getAllCars();
    }

    @Test
    public void whenGetCarsWithId_theReturnCar() throws Exception{
        Car testCar = new Car(1L, "Ford", "Mustang");
        Optional<Car> test = Optional.of(testCar);

        when(  service.getCarDetails(testCar.getCarId())).thenReturn( test);

        mvc.perform(
                get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is(testCar.getMaker())))
                .andExpect(jsonPath("$.model", is(testCar.getModel())));

        verify(service, times(1)).getCarDetails(1L);
    }

}
