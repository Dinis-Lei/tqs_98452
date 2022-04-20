package tqs.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@ExtendWith(MockitoExtension.class)
class APIServiceTest {
    
    @Mock
    private MyHttpClient client;

    private APIService service;

    @BeforeEach
    void setup() throws IOException, InterruptedException, ParseException{
        service = new APIService(client);
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        when(client.fetch("portugal", today.toString()))
            .thenReturn(new JSONParser().parse("{\"get\":\"history\",\"parameters\":{\"country\":\"portugal\",\"day\":\"2022-04-15\"},\"errors\":[],\"results\":3,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":null,\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":null,\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"4010223\",\"total\":40678788},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T17:30:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":\"+10459\",\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":\"+23\",\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"3995982\",\"total\":40534335},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T12:00:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143853,\"cases\":{\"new\":\"+10459\",\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366674\",\"total\":3719485},\"deaths\":{\"new\":\"+23\",\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"3995951\",\"total\":40534335},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T00:00:03+00:00\"}]}"));
    
        when(client.fetch("portugal", yesterday.toString()))
            .thenReturn(new JSONParser().parse("{\"get\":\"history\",\"parameters\":{\"country\":\"portugal\",\"day\":\"2022-04-14\"},\"errors\":[],\"results\":3,\"response\":[{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":null,\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":null,\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"4010223\",\"total\":40678788},\"day\":\"2022-04-14\",\"time\":\"2022-04-15T17:30:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143772,\"cases\":{\"new\":\"+10459\",\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366677\",\"total\":3719485},\"deaths\":{\"new\":\"+23\",\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"3995982\",\"total\":40534335},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T12:00:03+00:00\"},{\"continent\":\"Europe\",\"country\":\"Portugal\",\"population\":10143853,\"cases\":{\"new\":\"+10459\",\"active\":null,\"critical\":61,\"recovered\":null,\"1M_pop\":\"366674\",\"total\":3719485},\"deaths\":{\"new\":\"+23\",\"1M_pop\":\"2168\",\"total\":21993},\"tests\":{\"1M_pop\":\"3995951\",\"total\":40534335},\"day\":\"2022-04-15\",\"time\":\"2022-04-15T00:00:03+00:00\"}]}"));
    
    } 

    @Test
    void getDataByCountryTest() throws IOException, InterruptedException, ParseException, java.text.ParseException{
        System.out.println(service);
        
        assertThat(service.getDataByCountry("portugal", 2))
            .hasSize(2)
            .extracting(CovidData::getCountry, CovidData::getDay, CovidData::getTotalCases)
            .contains(tuple("Portugal","2022-04-15",3719485L),tuple("Portugal","2022-04-14",3719485L));

        assertThat(service.getDataByCountry("france", 3)).isEmpty();

    }
    


}
