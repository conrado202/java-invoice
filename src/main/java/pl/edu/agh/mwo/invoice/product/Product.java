package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) /* throws IllegalArgumentException */ {
        
    	if(name == null || name.equals("") || tax == null) {
    		throw new IllegalArgumentException("You cannot add product with null name OR empty name OR null taxPercent");
    	}
    	
    	if(price == null || price.compareTo(BigDecimal.ZERO) < 0) {			// BigDecimal.ZERO to bedzie to samo co new BigDecimal("0")
    		throw new IllegalArgumentException("You cannot add product with null price OR price less than 0");
    	}
    	
    	this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {
    	return this.price.multiply(this.taxPercent).add(this.price);
    }
}
