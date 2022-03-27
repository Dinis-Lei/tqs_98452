package tqs.carservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CarController {

    @Autowired
    private CarManagerService service;

    @PostMapping("cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved = service.save(car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("cars")
    public List<Car> getAllCars(){
        return service.getAllCars();
    }

    @GetMapping("cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
            Optional<Car> car = service.getCarDetails(id);
            if (car.isPresent()){
                return ResponseEntity.ok().body(car.get());
            }
            return null;
            
            
    }
}
