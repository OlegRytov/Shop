package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();
    Product product1 = new Product(1, "Телевизор", 27_000);
    Product product2 = new Product(11, "Микроволновка", 9_000);
    Product product3 = new Product(21, "Моноблок", 31_000);
    Product product4 = new Product(33, "Хлебопеч", 10_000);
    Product product5 = new Product(111, "Утюг", 2_500);
    Product product6 = new Product(41, "Монитор", 17_000);

    @BeforeEach
    public void setup() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
        repo.add(product5);
    }

    @Test
    public void shouldRemoveProductById() {
        repo.removeById(21);
        Product[] expected = {product1, product2, product4, product5};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveProductNoFoundId() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(110);
        });
    }

    @Test
    public void shouldAddNewProduct() {
        repo.add(product6);
        Product[] expected = {product1, product2, product3, product4, product5, product6};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddProductWithAlreadyExistingId() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product3);
        });
    }
}