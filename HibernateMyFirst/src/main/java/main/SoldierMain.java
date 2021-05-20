package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.sda.entity.Soldier;

public class SoldierMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(new Soldier(null, "Martin", "Smith"));

        Soldier soldier = session.get(Soldier.class, 1L);

        if (soldier != null) {
            System.out.println(soldier);
        } else {
            System.out.println("There is no element in this row.");
        }

        transaction.commit();
        session.close();
        sessionFactory.close();

    }

}
