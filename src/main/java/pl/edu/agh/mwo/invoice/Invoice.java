package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.security.KeyStore.Entry;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	
    private Map<Product, Integer> products = new LinkedHashMap<>();
    
    // Nie potrzebujemy tu konsturktora, poniewaz mamy to zalatwione na poziomie deklaracji
    // LinedHashMap

    public void addProduct(Product product) {
        products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
    	products.put(product, quantity);
    	
    }

    public BigDecimal getSubtotal() {
    	BigDecimal sum = BigDecimal.ZERO;
    	
        for(Product product : this.products.keySet()) {			// Keyset zwraca mi zbior kluczy dla kazdego produktyu zawartego w tej mapie
        	Integer quantity = this.products.get(product);		// Zmienna quantity pobiera informacje na temat ilosci dla danego produktu
        	
        	sum = sum.add(product.getPrice().multiply(new BigDecimal(quantity)));
        }
        
        return sum;
    }


	public BigDecimal getTax() {
		
		BigDecimal taxSum = BigDecimal.ZERO;
		
		for(Product product : this.products.keySet()) {			
        	Integer quantity = this.products.get(product);	
        	
        	taxSum = taxSum.add(product.getTaxPercent().multiply(new BigDecimal(quantity)));
		}
		
		return taxSum;
	}
		
    public BigDecimal getTotal() {
        return BigDecimal.ZERO;
    }
}
