package ru.gb.Ex.webApp.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.Ex.webApp.entities.Product;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String title;

    private int price;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
