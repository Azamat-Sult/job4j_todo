package ru.job4j.hibernate.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmCars {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            CarModel model1 = CarModel.of("model1");
            session.save(model1);

            CarModel model2 = CarModel.of("model2");
            session.save(model2);

            CarModel model3 = CarModel.of("model3");
            session.save(model3);

            CarModel model4 = CarModel.of("model4");
            session.save(model4);

            CarModel model5 = CarModel.of("model5");
            session.save(model5);

            CarBrand brand1 = CarBrand.of("brand1");

            brand1.addCarModel(session.load(CarModel.class, 1));
            brand1.addCarModel(session.load(CarModel.class, 2));
            brand1.addCarModel(session.load(CarModel.class, 3));
            brand1.addCarModel(session.load(CarModel.class, 4));
            brand1.addCarModel(session.load(CarModel.class, 5));

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
