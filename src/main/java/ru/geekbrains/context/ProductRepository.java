package ru.geekbrains.context;

import org.springframework.stereotype.Component;
import ru.geekbrains.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {

    List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
          new Product(1, "Loaf", 33),
          new Product(2, "Milk", 50),
          new Product(3, "Sugar", 38),
          new Product(4, "Solt", 20),
          new Product(5, "Soda", 18)
        ));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException());
    }

}
