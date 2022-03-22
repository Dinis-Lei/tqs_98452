package tqsIntegration;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
public class AddressResolverIT {
    
    @Mock
    private TqsBasicHttpClient client;

    private AddressResolver resolver;

    @BeforeEach
    void setup(){
        client = mock(TqsBasicHttpClient.class);

        resolver = new AddressResolver(client);

    }


    @Test
    void testFindAddressForLocation() throws ParseException, URISyntaxException, IOException, org.json.simple.parser.ParseException {
        
        // Correct location
        assertEquals(
            resolver.findAddressForLocation(40.6318, -8.658),
            new Address("Parque Estacionamento da Reitoria - Univerisdade de Aveiro",
            "Glória e Vera Cruz",
            "Centro", "3810-193", null));

        // Incorrect location
        assertNotEquals(resolver.findAddressForLocation(40.6318, -200), 
        new Address("Parque Estacionamento da Reitoria - Univerisdade de Aveiro",
        "Glória e Vera Cruz",
        "Centro", "3810-193", null));

        // Correct location
        assertEquals(resolver.findAddressForLocation(40.6318, -200), 
        new Address("Ashley Melisse Boulevard",
        "Jacksonville",
        "FL", "32225", null));
    }
}
