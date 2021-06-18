package br.com.supera.game.store;

import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import br.com.supera.game.store.domain.dto.ProductDTO;
import br.com.supera.game.store.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartServiceTest {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Test
    public void addNonExitentProduct() {
        ProductDTO p = new ProductDTO();
        p.setId(123123);
        p.setImage("");
        p.setName("Produto não existente");
        p.setPrice(new BigDecimal(0.0f));
        p.setScore((short) 0);
        assertThrows(ResponseStatusException.class, () -> shoppingCartService.addProduct(p));
    }

    @Test
    public void removeNonExitentProduct() {
        ProductDTO p = new ProductDTO();
        p.setId(123123);
        p.setImage("");
        p.setName("Produto não existente");
        p.setPrice(new BigDecimal(0.0f));
        p.setScore((short) 0);
        assertThrows(ResponseStatusException.class, () -> shoppingCartService.removeProduct(p));
    }

}
