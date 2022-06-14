package ru.gb.Ex.webApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.repositories.ProductRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;


    public List<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(int id) {
        return repo.findById((long) id);
    }

    public String addProduct(Product product) {
        repo.save(product);
        return "Создан: " + product;
    }


    public String deleteProduct(int id) {
        String deleted = repo.findById((long) id).orElseThrow().getTitle();
        repo.deleteById((long) id);
        return "Удален: " + deleted;
    }

    public List<Product> findProductByPriceAfter (int min_price) {
        return repo.findProductByPriceAfter(min_price);
    }

    public List<Product> findProductByPriceBefore (int max_price) {
        return repo.findProductByPriceBefore(max_price);
    }

    public List<Product> findProductByPriceBetween (int min_price, int max_price) {
        return repo.findProductByPriceBetween(min_price, max_price);
    }

    public String errorMsg() {
        return "По вашему запросу ничего не найдено";
    }

}

