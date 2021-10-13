package ru.job4j.hibernate.cars2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmCarEngineOneToOneInit {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Engine engine1 = Engine.of("engine 1", 111111);
            Engine engine2 = Engine.of("engine 2", 222222);
            Engine engine3 = Engine.of("engine 3", 333333);
            Car car1 = Car.of("model 1");
            Car car2 = Car.of("model 2");
            Car car3 = Car.of("model 3");

            car1.setEngine(engine1);
            engine1.setCar(car1);
            session.save(car1);

            car2.setEngine(engine2);
            engine2.setCar(car2);
            session.save(car2);

            car3.setEngine(engine3);
            engine3.setCar(car3);
            session.save(car3);

            session.getTransaction().commit();
            session.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
