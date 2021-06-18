package br.com.supera.game.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.supera.game.store.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
