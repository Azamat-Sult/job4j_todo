package ru.job4j.hibernate.books;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmBooksInit {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book book1 = Book.of("Book 1");
            Book book2 = Book.of("Book 2");
            Book book3 = Book.of("Book 3");

            session.save(book1);
            session.save(book2);
            session.save(book3);

            Author author1 = Author.of("Author 1");
            Author author2 = Author.of("Author 2");

            author1.addBook(book1);
            author1.addBook(book2);

            author2.addBook(book2);
            author2.addBook(book3);

            session.save(author1);
            session.save(author2);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
