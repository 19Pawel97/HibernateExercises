package pl.sda.onetomany.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.sda.onetomany.entity.Car;
import pl.sda.onetomany.entity.Driver;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Car car1 = new Car(null, "Mercedes", "Benz", 5, null);
        Car car2 = new Car(null, "Aston Martin", "DB510", 30, null);
        Driver owner = new Driver(null, "Paul", "Smith", 6, Arrays.asList(car1,car2));
        car1.setOwner(owner);
        car2.setOwner(owner);

        session.save(car1);
        session.save(car2);
        session.save(owner);

        Driver ownerFromDb = session.get(Driver.class, 1L);

        System.out.println(ownerFromDb.getName() + " " + ownerFromDb.getSurname());

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
