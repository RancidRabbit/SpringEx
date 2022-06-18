package ru.gb.Ex.webApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    public Page<Product> findAll(int pageIndex, int pageSize) {
        return repo.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(int id) {
        return repo.findById((long) id);
    }

    public String addProduct(Product product) {
        repo.save(product);
        return "Создан: " + product;
    }


    public void deleteProduct(int id) {
        repo.deleteById((long) id);
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

