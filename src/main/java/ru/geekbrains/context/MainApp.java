package ru.geekbrains.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.Product;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Cart cart = context.getBean(Cart.class);

        Scanner sc = new Scanner(System.in);
        boolean go = true;

        info();

        while (go){
            String [] command = sc.nextLine().split(" ");
            switch (command[0]) {
                case "exit":
                    go = false;
                    break;
                case "ass":
                    System.out.println("Сегодня доступны продукты:");
                    cart.assortiment().forEach(product -> {
                        System.out.println(product.toString());
                    });
                    break;
                case "cart_add":
                    if (!cart.addProduct(Long.parseLong(command[1]))) {
                        System.out.println("Нет такого продукта.");
                    } else {
                        System.out.println("Продукт успешно добавлен в корзину.");
                    }
                    break;
                case  "cart_del":
                    if (!cart.delProduct(Long.parseLong(command[1]))) {
                        System.out.println("Невозможно удалить из корзины -> корзина пуста... или нет такого продукта.");
                    } else {
                        System.out.println("Продукт удален из корзины");
                    }
                    break;
                case "cart_show":
                    System.out.println(cart.toString());
                    break;
                default:
                    info();
            }
        }
    }

    private static void info() {
        System.out.println("Используйте команды:");
        System.out.println("ass - список доступных продуктов");
        System.out.println("cart_add ID - положить продукт с номером ID");
        System.out.println("cart_del ID - удалить продукт с номером ID");
        System.out.println("cart_show - показать содержимое корзины");
        System.out.println("exit - выход");
    }
}
