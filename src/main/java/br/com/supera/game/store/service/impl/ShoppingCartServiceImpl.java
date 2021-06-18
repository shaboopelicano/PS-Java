package br.com.supera.game.store.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.supera.game.store.domain.Product;
import br.com.supera.game.store.domain.ShoppingCart;
import br.com.supera.game.store.domain.dto.ProductDTO;
import br.com.supera.game.store.service.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    private ShoppingCart shoppingCart;

    @Override
    public void addProduct(ProductDTO product) {
        
    }

    @Override
    public void removeProduct(ProductDTO product) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateValues() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public BigDecimal getInvoice() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> getProducts() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
