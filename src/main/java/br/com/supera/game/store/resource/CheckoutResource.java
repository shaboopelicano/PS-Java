package br.com.supera.game.store.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.domain.Product;
import br.com.supera.game.store.domain.dto.ProductDTO;
import br.com.supera.game.store.service.ShoppingCartService;

@RestController
@RequestMapping("/checkout")
public class CheckoutResource {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public CheckoutResource(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping()
    public ResponseEntity<?> listProducts(@RequestParam("price") double price, @RequestParam("score") double score,
            @RequestParam("order") String order) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO product) {
        shoppingCartService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO product) {
        shoppingCartService.removeProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
