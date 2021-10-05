package ru.job4j.todo.persistence;

import ru.job4j.todo.model.Item;

import java.util.List;

public interface Store extends AutoCloseable {

    Item add(Item item);

    void update(Item item);

    List<Item> findAll();

    Item findById(int id);

}