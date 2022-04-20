package tqs.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
    private Logger logger = Logger.getLogger("apilogger");

    @GetMapping("/{country}")
    public ResponseEntity<List<CovidData>> getDataByCountry(@PathVariable(value = "country") String country, @RequestParam(defaultValue = "3") int numberOfDays)
      throws IOException, InterruptedException, ParseException {

        logger.info(String.format("APIController: Request for country:%s numberOfdays:%s", country, numberOfDays));
        List<CovidData> data = service.getDataByCountry(country.toLowerCase(), numberOfDays);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/cachestats")
    public ResponseEntity<Map<String,Double>> getCacheStats(){
      logger.info("APIController: Request for cache statistics:");
      return ResponseEntity.ok().body(service.getCacheStats());
    }


}
