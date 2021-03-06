package ru.job4j.todo.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.function.Function;

public class HbmStore implements Store, AutoCloseable {

    private static final class Lazy {
        private static final HbmStore INST = new HbmStore();
    }

    public static HbmStore instOf() {
        return HbmStore.Lazy.INST;
    }

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();


    @Override
    public Item addTask(Item item) {
        this.tx(
                session -> session.save(item)
        );
        return item;
    }

    @Override
    public void updateTask(int id, boolean done) {
        this.tx(
                session -> {
                    String hql = "update ru.job4j.todo.model.Item i set i.done= :done where i.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("done", !done);
                    hqlQuery.setParameter("id", id);
                    return hqlQuery.executeUpdate();
                }
        );
    }

    @Override
    public List<Item> findAllTasks() {
        return this.tx(
                session -> session.createQuery("select distinct i from Item i join fetch i.categories").list()
        );
    }

    @Override
    public List<Category> findAllCategories() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.todo.model.Category").list()
        );
    }

    @Override
    public Category findCategoryById(String id) {
        return this.tx(
                session -> {
                    String hql = "from ru.job4j.todo.model.Category where id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", Integer.parseInt(id));
                    return (Category) hqlQuery.uniqueResult();
                });
    }

    @Override
    public User addUser(User user) {
        this.tx(
                session -> session.save(user)
        );
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        return this.tx(
                session -> {
                    String hql = "from ru.job4j.todo.model.User where email = :email";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("email", email);
                    return (User) hqlQuery.uniqueResult();
                });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}