package tqs.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class APIService {
    
    private String baseURL1 = "https://covid-193.p.rapidapi.com/";



    public CovidData getDataByCountry(String country) throws IOException, InterruptedException, ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);

        String url = new StringBuilder(baseURL1)
            .append("history?country=")
            .append(country)
            .append("&day=")
            .append(today)
            .toString();

        HttpRequest request = HttpRequest.newBuilder()
		    .uri(URI.create(url))
		    .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
		    .header("X-RapidAPI-Key", "b6283b0d21msh07f9ad38d06a37ap1b7bcejsn962972e338c1")
		    .method("GET", HttpRequest.BodyPublishers.noBody())
		    .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject obj = (JSONObject) new JSONParser().parse(response.body());
        JSONObject resp = (JSONObject)((JSONArray) obj.get("response")).get(0);
        JSONObject cases = (JSONObject) resp.get("cases");
        JSONObject deaths = (JSONObject) resp.get("deaths");
        JSONObject tests = (JSONObject) resp.get("tests");
        //System.out.println(obj.getJSONObject());
        
        CovidData data = new CovidData(country, 
                                        (String)cases.get("new"),
                                        (Long)cases.get("active"), 
                                        (Long)cases.get("total"), 
                                        (String)deaths.get("new"), 
                                        (Long)deaths.get("total"),
                                        (Long)tests.get("total"),
                                        today);

        return data;
    }

    public JSONObject getStatisticsByCountry(String country) throws IOException, InterruptedException, ParseException{
        
        String url = new StringBuilder(baseURL1).append("statistics?country=").append(country).toString();
        
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(URI.create(url))
        	.header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
        	.header("X-RapidAPI-Key", "b6283b0d21msh07f9ad38d06a37ap1b7bcejsn962972e338c1")
        	.method("GET", HttpRequest.BodyPublishers.noBody())
        	.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject obj = (JSONObject) new JSONParser().parse(response.body());
        return obj;
    }


}
