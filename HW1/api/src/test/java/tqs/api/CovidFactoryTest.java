package tqs.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;


public class CovidFactoryTest {
    
    @Test
    void factoryTest() throws ParseException{

        String correct = "[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":null,\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":null,\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"4010223\",\"total\":40678788},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T17:30:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":\"+10459\",\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":\"+23\",\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"3995982\",\"total\":40534335},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T12:00:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143853,\"cases\":{\"new\":\"+10459\",\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366674\",\"total\":3719485},\"deaths\":{\"new\":\"+23\",\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"3995951\",\"total\":40534335},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T00:00:03+00:00\"}]";

        JSONParser parser = new JSONParser();
        JSONArray resp = (JSONArray) parser.parse(correct);

        CovidFactory factory = new CovidFactory();

        CovidData test1 = factory.covidFactory(resp);
        
        assertThat(test1.getCountry()).isEqualTo("Portugal");

        JSONArray wrong = new JSONArray();

        CovidData test2 = factory.covidFactory(wrong);
        assertThat(test2.getCountry()).isNull();

        // Test if JSON is missing keys (ex: country, cases, day, etc...)
        String wrong2 = "[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"deaths\":{\"new\":null,\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"4010223\",\"total\":40678788},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T17:30:03+00:00\"}]";
        String wrong3 = "[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":null,\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"tests\":{\"1M_pop\":\"4010223\",\"total\":40678788},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T17:30:03+00:00\"}]";
        String wrong4 = "[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":null,\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":null,\"1M_pop\":\"2168\",\"total\":21993},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T17:30:03+00:00\"}]";
        String wrong5 = "[{\"continent\":\"Europe\",\"population\":10143772,\"cases\":{\"new\":null,\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":null,\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"4010223\",\"total\":40678788},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T17:30:03+00:00\"}]";
        String wrong6 = "[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":null,\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":null,\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"4010223\",\"total\":40678788},\"time\":\"2022-04-15T17:30:03+00:00\"}]";

        ArrayList<String> wrongs = new ArrayList<>();

        wrongs.add(wrong2);
        wrongs.add(wrong3);
        wrongs.add(wrong4);
        wrongs.add(wrong5);
        wrongs.add(wrong6);       

        for (String w : wrongs){
            JSONArray wrongResp = (JSONArray) parser.parse(w);
            System.out.println(wrongResp);
            CovidData test3 = factory.covidFactory(wrongResp);
            assertThat(test3.getCountry()).isNull();
        }

    }
}
