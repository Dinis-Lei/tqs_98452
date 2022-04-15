package tqs.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

public class CacheTest {

    private Cache cache;

    @BeforeEach
    void setup() throws ParseException {
        cache = new Cache(3, 1, 5);

        CovidData data1 = new CovidData("portugal", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13");
        CovidData data2 = new CovidData("portugal", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-12");
        CovidData data3 = new CovidData("usa", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13");

        cache.put("portugal:2022-04-13", data1);
        cache.put("portugal:2022-04-12", data2);
        cache.put("usa:2022-04-13", data3);
    }


    @Test
    void testCleanup() throws InterruptedException, ParseException {
        Thread.sleep(2000);

        assertThat(cache.size()).isEqualTo(3);
        CovidData data1 = new CovidData("france", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13");               
        cache.put("france:2022-04-13", data1);
        assertThat(cache.size()).isEqualTo(4);
        Thread.sleep(1500);
        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get("france:2022-04-13"))
            .extracting(CovidData::getDay, CovidData::getCountry)
            .contains(
                "2022-04-13", "france"
            );
        assertThat(cache.get("portugal")).isNull();

    }

    @Test
    void testGet() {

        CovidData data = cache.get("portugal:2022-04-13");
        assertThat(data)
            .extracting(CovidData::getDay, CovidData::getCountry)
            .contains(
                "2022-04-13", "portugal"
            );

        assertThat(cache.get("france:2022-04-13")).isNull();

    }

    @Test
    void testPut() throws ParseException {
      
        CovidData data = new CovidData("france", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13");
       
        cache.put("france:2022-04-13", data);

        assertThat(cache.size()).isEqualTo(4);

        assertThat(cache.get("france:2022-04-13"))
            .extracting(CovidData::getDay, CovidData::getCountry)
            .contains(
                "2022-04-13", "france"
            );
        cache.put("france2", data);
        cache.get("portugal:2022-04-13");
        cache.get("portugal:2022-04-13");
        cache.put("france3", data);
        
        assertThat(cache.size()).isEqualTo(5);
        assertThat(cache.get("usa")).isNull();
    }

    @Test
    void testRemove() {

        cache.remove("usa:2022-04-13");

        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get("usa:2022-04-13")).isNull();

    }

}
