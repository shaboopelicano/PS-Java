package br.com.supera.game.store.domain.dto;

import java.math.BigDecimal;

import br.com.supera.game.store.domain.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private int quantity;
    private long id;
    private String name;
    private BigDecimal price;
    private short score;
    private String image;

    public Product toProduct() {
        Product p = new Product();
        p.id = id;
        p.name = name;
        p.price = price;
        p.score = score;
        p.image = image;
        return p;
    }
}
