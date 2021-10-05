package ru.job4j.todo.service;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.persistence.HbmStore;

import java.util.List;

public class TodoService {

    private static final class Lazy {
        private static final TodoService INST = new TodoService();
    }

    public static TodoService instOf() {
        return TodoService.Lazy.INST;
    }

    public void addTask(Item item) {
        HbmStore.instOf().add(item);
    }

    public List<Item> findAllTasks() {
        return HbmStore.instOf().findAll();
    }

    public void changeTaskStatus(int id, boolean done) {
        Item itemToChange = HbmStore.instOf().findById(id);
        itemToChange.setDone(!done);
        HbmStore.instOf().update(itemToChange);
    }
}
