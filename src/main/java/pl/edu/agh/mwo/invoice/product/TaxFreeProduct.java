package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class TaxFreeProduct extends Product {
    public TaxFreeProduct(String name, BigDecimal price) /* throws IllegalArgumentException */ {
        super(name, price, BigDecimal.ZERO);
    }
}
