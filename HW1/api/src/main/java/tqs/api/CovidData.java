package tqs.api;

import java.text.ParseException;

import org.json.simple.JSONObject;

public class CovidData {
   
    private String country;
    private Long newCases;
    private Long activeCases;
    private Long totalCases;
    private Long newDeaths;
    private Long totalDeaths;
    private Long totalTests;
    private String day;
    
    public CovidData(String country, String newCases, Long activeCases, Long totalCases, String newDeaths, Long totalDeaths,
            Long totalTests, String day) throws ParseException {
        this.country = country;
        this.newCases = newCases != null? Long.parseLong(newCases.replace("+", "")):null;
        this.activeCases = activeCases;
        this.totalCases = totalCases;
        this.newDeaths = newDeaths != null? Long.parseLong(newDeaths.replace("+", "")):null;
        this.totalDeaths = totalDeaths;
        this.totalTests = totalTests;
        this.day = day;
    }

    public CovidData(JSONObject obj) throws ParseException{
        JSONObject cases = (JSONObject) obj.get("cases");
        JSONObject deaths = (JSONObject) obj.get("deaths");
        JSONObject tests = (JSONObject) obj.get("tests");

        this.country = (String) obj.get("country");
        this.day = (String) obj.get("day");

        this.newCases = cases.get("new") != null ? Long.parseLong( ((String) cases.get("new")).replace("+", "")) : null;
        this.activeCases = (Long) cases.get("active");
        this.totalCases = (Long) cases.get("total"); 
        this.newDeaths = newDeaths != null ? Long.parseLong(((String) deaths.get("new")).replace("+", "")) : null;
        this.totalDeaths = (Long) deaths.get("total");
        this.totalTests = (Long) tests.get("total");

    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public Long getNewCases() {
        return newCases;
    }


    public void setNewCases(Long newCases) {
        this.newCases = newCases;
    }


    public Long getActiveCases() {
        return activeCases;
    }


    public void setActiveCases(Long activeCases) {
        this.activeCases = activeCases;
    }


    public Long getTotalCases() {
        return totalCases;
    }


    public void setTotalCases(Long totalCases) {
        this.totalCases = totalCases;
    }


    public Long getNewDeaths() {
        return newDeaths;
    }


    public void setNewDeaths(Long newDeaths) {
        this.newDeaths = newDeaths;
    }


    public Long getTotalDeaths() {
        return totalDeaths;
    }


    public void setTotalDeaths(Long totalDeaths) {
        this.totalDeaths = totalDeaths;
    }


    public Long getTotalTests() {
        return totalTests;
    }


    public void setTotalTests(Long totalTests) {
        this.totalTests = totalTests;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "CovidData [activeCases=" + activeCases + ", country=" + country + ", day=" + day + ", newCases="
                + newCases + ", newDeaths=" + newDeaths + ", totalCases=" + totalCases + ", totalDeaths=" + totalDeaths
                + ", totalTests=" + totalTests + "]";
    }
}
