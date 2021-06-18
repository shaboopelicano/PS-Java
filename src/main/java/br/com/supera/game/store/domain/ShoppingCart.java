package br.com.supera.game.store.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.supera.game.store.domain.Product;

@Component
@Scope("singleton")
public class ShoppingCart {

    private List<Product> products;
    private BigDecimal shippingCost;
    private BigDecimal partialCost;
    private BigDecimal totalInvoice;

    public ShoppingCart() {
        products = new ArrayList<>();
        shippingCost = BigDecimal.valueOf(0.0D);
        partialCost = BigDecimal.valueOf(0.0D);
        totalInvoice = BigDecimal.valueOf(0.0D);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public BigDecimal getPartialCost() {
        return partialCost;
    }

    public void setPartialCost(BigDecimal partialCost) {
        this.partialCost = partialCost;
    }

    public BigDecimal getTotalInvoice() {
        return totalInvoice;
    }

    public void setTotalInvoice(BigDecimal totalInvoice) {
        this.totalInvoice = totalInvoice;
    }

    

}
