package tqs.carservice;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;

    @Test
    public void whenFindCarById_thenReturnCar() {
        // arrange a new car and insert into db
        Car test = new Car("Ford", "Mustang");
        entityManager.persistAndFlush(test); //ensure data is persisted at this point

        // test the query method of interest
        Car found = repository.findById(test.getCarId()).orElse(null);
        assertThat( found ).isEqualTo(test);
    }

    @Test
    public void whenInvalidCarId_thenReturnOptionalEmpty() {
        Optional<Car> fromDb = repository.findById(-100L);
        assertThat(fromDb.isEmpty());
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car test1 = new Car("Ford", "Mustang");
        Car test2 = new Car("Renaut", "Clio");
        Car test3 = new Car("Toyota", "Corolla");

        entityManager.persist(test1);
        entityManager.persist(test2);
        entityManager.persist(test3);
        entityManager.flush();

        List<Car> allEmployees = repository.findAll();
          
        for (Car car : allEmployees) {
            System.out.println(car);
        }

        assertThat(allEmployees)
        .hasSize(3)
        .extracting(Car::getCarId)
        .containsOnly(test1.getCarId(), test2.getCarId(), test3.getCarId());
    }

}
