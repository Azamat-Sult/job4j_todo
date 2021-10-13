package ru.job4j.hibernate.cars2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmCarDriverManyToManyInit {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Car car1 = Car.of("model 1");
            Car car2 = Car.of("model 2");
            Car car3 = Car.of("model 3");

            Driver driver1 = Driver.of("driver 1");
            Driver driver2 = Driver.of("driver 2");
            Driver driver3 = Driver.of("driver 3");

            car1.addDriver(driver1);
            car1.addDriver(driver2);

            car2.addDriver(driver2);
            car2.addDriver(driver3);

            car3.addDriver(driver1);
            car3.addDriver(driver3);

            session.save(driver1);
            session.save(driver2);
            session.save(driver3);
            session.save(car1);
            session.save(car2);
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
