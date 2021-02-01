package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	
    private Map<Product, Integer> products = new LinkedHashMap<>();
    
    // Nie potrzebujemy tu konsturktora, poniewaz mamy to zalatwione na poziomie deklaracji
    // LiknedHashMap

    public void addProduct(Product product) {
        products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
    	products.put(product, quantity);
    	
    	if(quantity <= 0 || quantity == null) {			// BigDecimal.ZERO to bedzie to samo co new BigDecimal("0")
    		throw new IllegalArgumentException("You cannot have got no product OR negative number of products (less than 0)");
    	}
    	
    }

    public BigDecimal getTotalNettoPrice() {
    	
    	BigDecimal sum = BigDecimal.ZERO;
    	
        for(Product product : this.products.keySet()) {			// Keyset zwraca mi zbior kluczy dla kazdego produktyu zawartego w tej mapie
        	Integer quantity = this.products.get(product);		// Zmienna quantity pobiera informacje na temat ilosci dla danego produktu
        	
        	sum = sum.add(product.getNettoPrice().multiply(new BigDecimal(quantity)));
        }
        
        return sum;
    }
		
    public BigDecimal getTotalBruttoPrice() {
    	
        BigDecimal priceWithTaxSum = BigDecimal.ZERO;
		
		for(Product product : this.products.keySet()) {			
        	Integer quantity = this.products.get(product);	
        	
        	priceWithTaxSum = priceWithTaxSum.add(product.getPriceWithTax().multiply(new BigDecimal(quantity)));
		}
		
		return priceWithTaxSum;
    }
    
	public BigDecimal getTax() {
			
		return getTotalBruttoPrice().subtract(getTotalNettoPrice());
	}
}
