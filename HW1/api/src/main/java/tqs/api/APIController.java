package tqs.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class APIController {
    
    @Autowired
    private APIService service;

    @GetMapping("/{country}")
    public ResponseEntity<ArrayList<CovidData>> getDataByCountry(@PathVariable(value = "country") String country, @RequestParam(defaultValue = "3") int numberOfDays)
      throws IOException, InterruptedException, ParseException, java.text.ParseException{
        ArrayList<CovidData> data = service.getDataByCountry(country, numberOfDays);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/cachestats")
    public ResponseEntity<HashMap<String,Double>> getCacheStats(){
      return ResponseEntity.ok().body(service.getCacheStats());
    }


}
