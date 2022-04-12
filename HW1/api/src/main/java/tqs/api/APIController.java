package tqs.api;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {
    
    @Autowired
    private APIService service;

    @GetMapping("/{country}")
    public ResponseEntity<CovidData> getDataByCountry(@PathVariable(value = "country") String country) throws IOException, InterruptedException, ParseException{
        CovidData data = service.getDataByCountry(country);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/statistics/{country}")
    public ResponseEntity<JSONObject> getStatisticsByCountry(@PathVariable(value = "country") String country) throws IOException, InterruptedException, ParseException{
        JSONObject data = service.getStatisticsByCountry(country);
        return ResponseEntity.ok().body(data);
    }

}
