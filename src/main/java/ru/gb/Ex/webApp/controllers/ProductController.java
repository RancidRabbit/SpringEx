package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
       return productService.deleteProduct(id);
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/products/filter")
    public List<Product> priceFilter(@RequestParam(value = "min", required = false) String min_price,
                                         @RequestParam(value = "max",required = false) String max_price) {
        if (Optional.ofNullable(max_price).isPresent() & Optional.ofNullable(min_price).isEmpty()) return productService.findProductByPriceBefore(Integer.parseInt(max_price));
        if (Optional.ofNullable(min_price).isPresent() & Optional.ofNullable(max_price).isEmpty()) return productService.findProductByPriceAfter(Integer.parseInt(min_price));
        return productService.findProductByPriceBetween(Integer.parseInt(min_price),Integer.parseInt(max_price));
    }

}
