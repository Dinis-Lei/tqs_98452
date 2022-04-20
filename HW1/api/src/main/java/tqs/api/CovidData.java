package tqs.api;

public class CovidData {
   
    private String country;
    private Long newCases;
    private Long activeCases;
    private Long totalCases;
    private Long newDeaths;
    private Long totalDeaths;
    private Long totalTests;
    private String day;
    
    public CovidData(String country, Long newCases, Long activeCases, Long totalCases, Long newDeaths, Long totalDeaths,
            Long totalTests, String day) {
        this.country = country;
        this.newCases = newCases;
        this.activeCases = activeCases;
        this.totalCases = totalCases;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.totalTests = totalTests;
        this.day = day;
    }

    public CovidData() {
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
