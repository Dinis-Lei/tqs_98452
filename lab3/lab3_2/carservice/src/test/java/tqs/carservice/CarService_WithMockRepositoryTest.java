package tqs.carservice;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CarService_WithMockRepositoryTest {
    
    @InjectMocks
    private CarManagerService service;

    @Mock(lenient = true)
    private CarRepository repository;

    private Car testCar1 = new Car(1L, "Ford", "Mustang");
    private Car testCar2 = new Car(2L, "Renaut", "Clio");
    private Car testCar3 = new Car(3L, "Toyota", "Corolla");

    @BeforeEach
    public void setup(){
        List<Car> cars = Arrays.asList(testCar1, testCar2, testCar3);

        when(repository.save(testCar1)).thenReturn(testCar1);
        when(repository.save(testCar2)).thenReturn(testCar2);
        when(repository.save(testCar3)).thenReturn(testCar3);
        when(repository.findAll()).thenReturn(cars);
        when(repository.findById(testCar1.getCarId())).thenReturn(Optional.of(testCar1));
        when(repository.findById(10L)).thenReturn(null);
    }

    @Test
    void whenSaveCar_thenReturnCarTest(){

        assertThat(service.save(testCar1)).isEqualTo(testCar1);
        verify(repository).save(testCar1);
    }

    @Test
    void whenGetAllCars_thenReturnListCarsTest(){
        List<Car> cars = service.getAllCars();
        assertThat(cars)
            .hasSize(3)
            .extracting(Car::getCarId)
            .contains(testCar1.getCarId(), testCar2.getCarId(), testCar3.getCarId());

        verify(repository, VerificationModeFactory.times(1)).findAll();
    }

    @Test 
    void whenGetCarDetails_thenReturnCarTest(){
        assertThat(service.getCarDetails(testCar1.getCarId())).isEqualTo(Optional.of(testCar1));
        verify(repository).findById(testCar1.getCarId());
        assertThat(service.getCarDetails(10L)).isNull();
        verify(repository).findById(10L);
    }
    
}
