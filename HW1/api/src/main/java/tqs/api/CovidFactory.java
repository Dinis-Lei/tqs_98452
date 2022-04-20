package tqs.api;

import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CovidFactory {
    
    private Logger logger = Logger.getLogger("apilogger");

    public CovidData covidFactory(JSONArray resp){

        if( resp.isEmpty()){
            logger.info("CovidFacroty: Creating Empty Object");
            return new CovidData();
        }

        JSONObject obj = (JSONObject) resp.get(0);

        if(!obj.containsKey("cases") || !obj.containsKey("deaths") || !obj.containsKey("tests") || !obj.containsKey("country") || !obj.containsKey("day")){
            logger.info("CovidFacroty: Creating Empty Object");
            return new CovidData();
        }

        JSONObject cases = (JSONObject) obj.get("cases");
        JSONObject deaths = (JSONObject) obj.get("deaths");
        JSONObject tests = (JSONObject) obj.get("tests");

        String country = (String) obj.get("country");
        String day = (String) obj.get("day");

        Long newCases = cases.get("new") != null ? Long.parseLong( ((String) cases.get("new")).replace("+", "")) : null;
        Long activeCases = (Long) cases.get("active");
        Long totalCases = (Long) cases.get("total"); 
        Long newDeaths = deaths.get("new") != null ? Long.parseLong(((String) deaths.get("new")).replace("+", "")) : null;
        Long totalDeaths = (Long) deaths.get("total");
        Long totalTests = (Long) tests.get("total");

        CovidData data = new CovidData(country, newCases, activeCases, totalCases, newDeaths, totalDeaths, totalTests, day);
        logger.info(String.format("CovidFacroty: Creating data %s", data));
        return data;
    }
}
