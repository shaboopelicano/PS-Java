package br.com.supera.game.store.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ShoppingCart {

    private List<Product> products;
    private BigDecimal shippingCost;
    private BigDecimal partialCost;
    private BigDecimal totalInvoice;

    public static float SHIPPING_COST_PER_PRODUCT = 10.0f;
    public static float FREE_SHIPPING_COST_THRESHOLD = 250.0f;

    public ShoppingCart() {
        products = new ArrayList<>();
        shippingCost = BigDecimal.valueOf(0.0D);
        partialCost = BigDecimal.valueOf(0.0D);
        totalInvoice = BigDecimal.valueOf(0.0D);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        updateValues();
    }

    public void removeProduct(Product product) {
        Product foundProduct = products.stream().filter(p -> p.id == product.id).findFirst().get();
        products.remove(foundProduct);
        updateValues();
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public BigDecimal getPartialCost() {
        return partialCost;
    }

    public BigDecimal getTotalInvoice() {
        return totalInvoice;
    }

    public String checkoutValues() {
        String invoiceString = 
        "Valor frete R$:" + shippingCost.toString() +
         "\nValor produtos R$:" + partialCost.toString() +
         "\nValor total R$:" + totalInvoice.toString();
        return invoiceString;
    }

    private void updateValues() {

        shippingCost = new BigDecimal(0);
        partialCost = new BigDecimal(0);

        for (Product p : products) {
            shippingCost = shippingCost.add(new BigDecimal(SHIPPING_COST_PER_PRODUCT));
            partialCost = partialCost.add(p.price);
        }

        if (partialCost.compareTo(new BigDecimal(FREE_SHIPPING_COST_THRESHOLD)) == 1
                || partialCost.compareTo(new BigDecimal(FREE_SHIPPING_COST_THRESHOLD)) == 0) {
            shippingCost = new BigDecimal(0);
        }

        totalInvoice = new BigDecimal(partialCost.doubleValue() + shippingCost.doubleValue());
        totalInvoice = totalInvoice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

    }

}
