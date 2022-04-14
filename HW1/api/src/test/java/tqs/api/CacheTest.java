package tqs.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class CacheTest {

    private Cache cache;

    @BeforeEach
    void setup() throws ParseException{
        cache = new Cache(3, 1, 5);

        ArrayList<CovidData> test1 = new ArrayList<>(){
            {
                add(new CovidData("portugal", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13"));
                add(new CovidData("portugal", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-12"));
                add(new CovidData("portugal", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-11"));
            }
        };

        ArrayList<CovidData> test2 = new ArrayList<>(){
            {
                add(new CovidData("spain", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13"));
                add(new CovidData("spain", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-12"));
                add(new CovidData("spain", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-11"));
            }
        };

        ArrayList<CovidData> test3 = new ArrayList<>(){
            {
                add(new CovidData("usa", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13"));
                add(new CovidData("usa", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-12"));
                add(new CovidData("usa", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-11"));
            }
        };

        cache.put("portugal", test1);
        cache.put("spain", test2);
        cache.put("usa", test3);
    }


    @Test
    void testCleanup() throws ParseException, InterruptedException {
        Thread.sleep(2000);

        assertThat(cache.size()).isEqualTo(3);
        ArrayList<CovidData> test = new ArrayList<>(){
            {
                add(new CovidData("france", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13"));
                add(new CovidData("france", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-12"));
                add(new CovidData("france", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-11"));
            }
        };                
        cache.put("france", test);
        assertThat(cache.size()).isEqualTo(4);
        Thread.sleep(1000);
        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get("france"))
            .hasSize(3)
            .extracting(CovidData::getDay, CovidData::getCountry)
            .contains(
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-13"), "france"),
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-12"), "france"),
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-11"), "france")
            );
        assertThat(cache.get("portugal")).isNull();

    }

    @Test
    void testGet() throws ParseException {

        List<CovidData> data = cache.get("portugal");
        assertThat(data)
            .hasSize(3)
            .extracting(CovidData::getDay, CovidData::getCountry)
            .contains(
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-13"), "portugal"),
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-12"), "portugal"),
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-11"), "portugal")
            );

        assertThat(cache.get("france")).isNull();

    }

    @Test
    void testPut() throws ParseException, InterruptedException {

        ArrayList<CovidData> test = new ArrayList<>(){
            {
                add(new CovidData("france", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-13"));
                add(new CovidData("france", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-12"));
                add(new CovidData("france", "+5", 1234L, 100000L, "+2", 3000L, 5000L, "2022-04-11"));
            }
        };
        
        cache.put("france", test);

        assertThat(cache.size()).isEqualTo(4);

        assertThat(cache.get("france"))
            .hasSize(3)
            .extracting(CovidData::getDay, CovidData::getCountry)
            .contains(
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-13"), "france"),
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-12"), "france"),
                tuple(new SimpleDateFormat("yyyy-mm-dd").parse("2022-04-11"), "france")
            );
        cache.put("france2", test);
        cache.get("portugal");
        cache.get("spain");
        cache.put("france3", test);

        assertThat(cache.size()).isEqualTo(5);
        assertThat(cache.get("usa")).isNull();
    }

    @Test
    void testRemove() {

        cache.remove("usa");

        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get("usa")).isNull();

    }

}
