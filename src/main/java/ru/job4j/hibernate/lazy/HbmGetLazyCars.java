package ru.job4j.hibernate.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmGetLazyCars {
    public static void main(String[] args) {

        List<LazyCarBrand> brands = new ArrayList<>();

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            brands = session.createQuery(
                    "select distinct lcb from LazyCarBrand lcb join fetch lcb.lazyCarModels"
            ).list();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        for (LazyCarModel model : brands.get(0).getLazyCarModels()) {
            System.out.println(model);
        }
    }
}
