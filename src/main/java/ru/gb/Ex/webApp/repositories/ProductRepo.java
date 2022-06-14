package ru.gb.Ex.webApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.Ex.webApp.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

  List<Product> findProductByPriceAfter(int min_price);

  List<Product> findProductByPriceBefore(int max_price);

  List<Product> findProductByPriceBetween(int min_price, int max_price);

}

