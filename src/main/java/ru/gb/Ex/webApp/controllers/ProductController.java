package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductDTO> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex ) {
        if (pageIndex < 0) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10).map(ProductDTO::new);
    }

    @GetMapping("/products/{id}")
    public Optional<Product> findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        productService.addProduct(product);
        /* Смысл создания нового ProductDTO а не возврата интсанса пришедшего в Post ? */
        return new ProductDTO(product);
    }

    @GetMapping("/products/filter")
    public List<Product> priceFilter(@RequestParam(value = "min", required = false) String min_price,
                                         @RequestParam(value = "max",required = false) String max_price) {
        if (Optional.ofNullable(max_price).isPresent() & Optional.ofNullable(min_price).isEmpty()) return productService.findProductByPriceBefore(Integer.parseInt(max_price));
        if (Optional.ofNullable(min_price).isPresent() & Optional.ofNullable(max_price).isEmpty()) return productService.findProductByPriceAfter(Integer.parseInt(min_price));
        return productService.findProductByPriceBetween(Integer.parseInt(min_price),Integer.parseInt(max_price));
    }

}
