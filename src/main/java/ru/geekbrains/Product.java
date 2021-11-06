package ru.geekbrains;

public class Product {

    private Long id;
    private String title;
    private Long cost;

    public Product(long id, String title, long cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return id + ". " + title + ' ' + cost + "р.";
    }
}
