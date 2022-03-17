package tqs;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    
    private List<Stock> stocks = new ArrayList<>();
    private IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public double getTotalValue(){
        double total = 0;
        for (Stock stock: stocks){
            total += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }

        return total;
    }
}
