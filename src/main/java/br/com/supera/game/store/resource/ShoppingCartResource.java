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
@RequestMapping("/cart")
public class ShoppingCartResource {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartResource(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping()
    public ResponseEntity<?> listProducts(@RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "score", required = false) String score,
            @RequestParam(value = "order", required = false) String order) {
        List<Product> products = shoppingCartService.getProducts(price, score, order);
        return new ResponseEntity<>(products, HttpStatus.OK);
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

    @PostMapping("checkout")
    public ResponseEntity<?> attemptCheckout() {
        return new ResponseEntity<>(shoppingCartService.getInvoice(),HttpStatus.OK);
    }

}
