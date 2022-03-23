package tqsIntegration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tqs.Address;
import tqs.AddressResolver;
import tqs.TqsBasicHttpClient;

public class AddressResolverIT {
    
    private TqsBasicHttpClient client;

    private AddressResolver resolver;

    @BeforeEach
    void setup(){
        client = new TqsBasicHttpClient();

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
        assertNotEquals(resolver.findAddressForLocation(30.333472, -81.470448), 
        new Address("Parque Estacionamento da Reitoria - Univerisdade de Aveiro",
        "Glória e Vera Cruz",
        "Centro", "3810-193", null));

        // Correct location
        assertEquals(resolver.findAddressForLocation(30.333472, -81.470448), 
        new Address("Ashley Melisse Boulevard",
        "Jacksonville",
        "FL", "32225", null));
    }
}
