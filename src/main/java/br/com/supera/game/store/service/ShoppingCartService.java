package br.com.supera.game.store.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.supera.game.store.domain.Product;
import br.com.supera.game.store.domain.dto.ProductDTO;

public interface ShoppingCartService {

    public List<Product> getProducts();

    public void addProduct(ProductDTO product);

    public void removeProduct(ProductDTO product);

    public void updateValues();

    public BigDecimal getInvoice();

}
