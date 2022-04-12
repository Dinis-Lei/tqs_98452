package tqs.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(APIController.class)
public class APIController_WithMockServiceTest {
    
    @Autowired
    private MockMvc mvc; 

    @MockBean
    private APIService service;

    @Test
    public void whenGetDataByCountry_thenReturnJSONArrya() throws IOException, InterruptedException, ParseException{

        CovidData test = new CovidData("usa", "+1907", Long.valueOf(1132845), Long.valueOf(82104974), "+51", Long.valueOf(1012512), Long.valueOf(992036216), "2022-04-12");

        when(service.getDataByCountry("portugal")).thenReturn(test);

        // TODO: THIS

        //mvc.perform(
        //        post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(test))
        //        .andExpect(status().isCreated())
        //        .andExpect(jsonPath("$.maker", is("Ford")))
        //        .andExpect(jsonPath("$.model", is("Mustang")));


    }

}
