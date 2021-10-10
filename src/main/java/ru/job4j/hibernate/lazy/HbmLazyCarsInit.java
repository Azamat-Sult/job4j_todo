package ru.job4j.hibernate.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmLazyCarsInit {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            LazyCarBrand brand1 = LazyCarBrand.of("brand 1");

            LazyCarModel model1 = LazyCarModel.of("model 1", brand1);
            LazyCarModel model2 = LazyCarModel.of("model 2", brand1);
            LazyCarModel model3 = LazyCarModel.of("model 3", brand1);

            session.save(model1);
            session.save(model2);
            session.save(model3);

            brand1.addLazyCarModel(model1);
            brand1.addLazyCarModel(model2);
            brand1.addLazyCarModel(model3);

            session.save(brand1);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
