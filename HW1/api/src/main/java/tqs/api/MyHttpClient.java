package tqs.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MyHttpClient {

    private String baseURL1 = "https://covid-193.p.rapidapi.com/";
    private Logger logger = Logger.getLogger("apilogger");

    public Object fetch(String country, String date) throws IOException, InterruptedException, ParseException{
        String url = new StringBuilder(baseURL1)
            .append("history?country=")
            .append(country)
            .append("&day=")
            .append(date)
            .toString();

        logger.info(String.format("MyHttpClient: fetching url %s", url));
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
            .header("X-RapidAPI-Key", "b6283b0d21msh07f9ad38d06a37ap1b7bcejsn962972e338c1")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        logger.info(String.format("MyHttpClient: response %s", response));
        return new JSONParser().parse(response.body());
    }

}
