package tqs.api;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(APIController.class)
class APIController_WithMockServiceTest {
    
    @Autowired
    private MockMvc mvc; 

    @MockBean
    private APIService service;

    @Test
    void whenGetDataByCountry_thenReturnArrayList() throws Exception{

        ArrayList<CovidData> test = new ArrayList<>(){
            {
                add(new CovidData("portugal", 5L, 1234L, 100000L, 2L, 3000L, 5000L, "2022-04-13"));
                add(new CovidData("portugal", 5L, 1234L, 100000L, 2L, 3000L, 5000L, "2022-04-12"));
                add(new CovidData("portugal", 5L, 1234L, 100000L, 2L, 3000L, 5000L, "2022-04-11"));
            }
        }; 

        when(service.getDataByCountry("portugal", 3)).thenReturn(test);

        mvc.perform(
                get("/api/portugal").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].country", is("portugal")))
                .andExpect(jsonPath("$[0].day", is("2022-04-13")))
                .andExpect(jsonPath("$[1].country", is("portugal")))
                .andExpect(jsonPath("$[1].day", is("2022-04-12")))
                .andExpect(jsonPath("$[2].country", is("portugal")))
                .andExpect(jsonPath("$[2].day", is("2022-04-11")));
    }

    @Test
    void cacheStatsTest() throws Exception{

        HashMap<String,Double> cacheStats = new HashMap<>();

        cacheStats.put("hits", 11.0);                
        cacheStats.put("miss", 7.0);            
        cacheStats.put("total_requests", 18.0);            
        cacheStats.put("ratio", 11.0/18);                

        when(service.getCacheStats()).thenReturn(cacheStats);

        mvc.perform(
            get("/api/cachestats").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hits", is(11.0)))
            .andExpect(jsonPath("$.total_requests", is(18.0)))
            .andExpect(jsonPath("$.ratio", is(11.0/18)))
            .andExpect(jsonPath("$.miss", is(7.0)));
    }

}
