package tqs.api;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;



@Service
public class APIService {
    
    private Cache cache = new Cache(15, 2, 20);
    private MyHttpClient myHttpClient;

    public APIService(MyHttpClient client){
        myHttpClient = client;
    }

    public APIService(){
        myHttpClient = new MyHttpClient();
    }


    public ArrayList<CovidData> getDataByCountry(String country, int numberOfDays) throws IOException, InterruptedException, ParseException, java.text.ParseException{
        ArrayList<CovidData> data = new ArrayList<>();

        for(int i = 0; i < numberOfDays; i++){
            LocalDate today = LocalDate.now();
            LocalDate date = today.minusDays(i);

            String key = country + ":" + date;
            CovidData cached = cache.get(key);
            if (cached != null){
                data.add(cached);
            }
            else {
                JSONObject obj = (JSONObject) myHttpClient.fetch(country, date.toString());
                if (obj != null){
                    JSONObject resp = (JSONObject) ((JSONArray) obj.get("response")).get(0);
                    CovidData d = new CovidData(resp);
                    cache.put(key, d);
                    data.add(d); 
                }
            } 
        }
        return data;
    }

    public HashMap<String, Double> getCacheStats(){
        HashMap<String, Double> stats = new HashMap<>();
        stats.put("hits", Double.valueOf(cache.getHit()));
        stats.put("miss", Double.valueOf(cache.getMiss()));
        stats.put("total_requests", Double.valueOf(cache.getMiss()) + Double.valueOf(cache.getHit()));
        stats.put("ratio", cache.getRatio());
        return stats;
    }

}
