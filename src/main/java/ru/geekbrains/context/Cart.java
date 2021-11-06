package ru.geekbrains.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.geekbrains.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope ("prototype")
public class Cart {
    private ProductRepository productRepository;

    private List<Product> cart;

    @PostConstruct
    private void init() {
        cart = new ArrayList<>();
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean addProduct (Long id) {
        try {
            cart.add(productRepository.getProductById(id));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean delProduct (Long id) {
        if (cart.size() == 0) {
            return false;
        }
        try {
            return cart.remove(productRepository.getProductById(id));
        } catch (RuntimeException e) {
            return false;
        }
    }

    public List<Product> getCart() {
        return cart;
    }

    public List<Product> assortiment () {
        return productRepository.products;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Содержимое корзины: \n");
        if (cart.size() == 0) {
            str.append("Пусто, однако!");
        } else {
            for (Product product : cart) {
                str.append(product.toString() + "\n");
            }
        }

        return str.toString();
    }
}
