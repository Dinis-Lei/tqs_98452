package tqs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {

    @Mock 
    private IStockmarketService service;
    private StocksPortfolio portfolio;


    @BeforeEach
    public void setup(){  
        //service = mock(IStockmarketService.class);
        
        portfolio = new StocksPortfolio(service);

        portfolio.addStock(new Stock("stock1", 10));
        portfolio.addStock(new Stock("stock2", 20));
        portfolio.addStock(new Stock("stock3", 3));

        when(service.lookUpPrice("stock1")).thenReturn(15.0);
        when(service.lookUpPrice("stock2")).thenReturn(20.0);
        when(service.lookUpPrice("stock3")).thenReturn(5.0);
    }

    @Test
    public void testGetTotalValue() {
        //assertEquals(565.0, portfolio.getTotalValue());
        assertThat(portfolio.getTotalValue(), is(565.0));
    }
}
