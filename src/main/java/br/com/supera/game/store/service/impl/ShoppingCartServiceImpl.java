package br.com.supera.game.store.service.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.supera.game.store.domain.Product;
import br.com.supera.game.store.domain.ShoppingCart;
import br.com.supera.game.store.domain.dto.ProductDTO;
import br.com.supera.game.store.repository.ProductRepository;
import br.com.supera.game.store.service.ShoppingCartService;
import br.com.supera.game.store.util.ErrorMessages;
import lombok.extern.slf4j.Slf4j;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCart shoppingCart;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCart shoppingCart, ProductRepository productRepository) {
        this.shoppingCart = shoppingCart;
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductDTO product) {
        validateProduct(product);
        for (int i = 0; i < product.getQuantity(); i++)
            shoppingCart.addProduct(product.toProduct());
    }

    @Override
    public void removeProduct(ProductDTO product) {
        validateProduct(product);
        shoppingCart.removeProduct(product.toProduct());
    }

    @Override
    public String getInvoice() {
        return shoppingCart.checkoutValues();
    }

    private void validateProduct(ProductDTO product) {
        Optional<Product> p = productRepository.findById(product.getId());
        if (p.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessages.PRODUCT_NOT_FOUND);
    }

    @Override
    public List<Product> getProducts(String price, String score, String order) {
        List<Product> allProducts = shoppingCart.getProducts();
        List<Product> returningProducts = allProducts;
        if (price != null && !price.isEmpty()) {
            BigDecimal queryPrice = BigDecimal.valueOf(Double.valueOf(price));
            returningProducts = allProducts.stream().filter(p -> p.price.compareTo(queryPrice) == -1)
                    .collect(Collectors.toList());
        }
        if (score != null && !score.isEmpty()) {
            Long queryScore = Long.valueOf(score);
            returningProducts = returningProducts.stream().filter(p -> p.score > queryScore)
                    .collect(Collectors.toList());
        }
        if (order != null && order.isEmpty() && Boolean.valueOf(order)) {
            returningProducts = returningProducts.stream().sorted((o1,o2)->o1.name.compareTo(o2.name))
                    .collect(Collectors.toList());
        }

        return returningProducts;
    }

}
