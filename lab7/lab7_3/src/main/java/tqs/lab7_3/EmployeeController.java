package tqs.lab7_3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService service;

    /**
     * Using constructor Injection instead of @autowired
     * when using a constructor to set injected properties, you do not have to provide the autowire annotation
     * @param employeeService
     */
    public EmployeeController(EmployeeService employeeService) {
        this.service = employeeService;
    }

    @PostMapping("/employees" )
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employee) {
        HttpStatus status = HttpStatus.CREATED;
        Employee saved = service.save( employee.toEmployeeEntity() );
        return new ResponseEntity<>(saved, status);
    }


    @GetMapping(path="/employees" )
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

}

