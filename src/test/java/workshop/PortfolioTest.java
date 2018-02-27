package workshop;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioTest {

	@Test
	public void testMarketValue(){

	   //Create the mock object of stock service
		StockService stockService = mock(StockService.class);

		//Create a portfolio object which is to be tested
		Portfolio portfolio = new Portfolio();

	   //set the stockService to the portfolio
	   portfolio.setStockService(stockService);

	   //Creates a list of stocks to be added to the portfolio
	   List<Stock> stocks = new ArrayList<Stock>();
	   Stock googleStock = new Stock("1","Google", 10);
	   Stock microsoftStock = new Stock("2","Microsoft",100);

	   stocks.add(googleStock);
	   stocks.add(microsoftStock);

	   //add stocks to the portfolio
	   portfolio.setStocks(stocks);

	   //mock the behavior of stock service to return the value of various stocks
	   	when(stockService.getPrice(googleStock)).thenReturn(50.00);
	   	when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

	   	double marketValue = portfolio.getMarketValue();

	   	assertEquals(100500.0, marketValue, 0.01);

	}


}
