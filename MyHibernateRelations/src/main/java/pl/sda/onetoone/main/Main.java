package pl.sda.onetoone.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.sda.onetoone.entity.Dog;
import pl.sda.onetoone.entity.Owner;

import javax.persistence.Query;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Owner owner = new Owner(null, "Paul");
        Dog dog = new Dog(null, "Reksio", 10, owner);

        session.save(owner);
        session.save(dog);

        Dog dog1 = session.get(Dog.class, 1L);
        System.out.println(dog1);
        System.out.println(dog1.getOwner());

        String hql = "From Dog where name = :nameParameter";

        Query query = session.createQuery(hql);

        query.setParameter("nameParameter", "Reksio");

        query.getResultStream()
                .forEach(System.out::println);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
