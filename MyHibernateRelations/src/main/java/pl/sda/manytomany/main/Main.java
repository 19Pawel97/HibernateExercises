package pl.sda.manytomany.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.sda.manytomany.entity.Employee;
import pl.sda.manytomany.entity.Employer;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Employee employee1 = new Employee(null, "Jan", "Kowalski", null);
        Employee employee2 = new Employee(null, "Marcin", "Najman", null);
        Employer employer1 = new Employer(null, "Anna", "Smaruj", Arrays.asList(employee1,employee2));
        Employer employer2 = new Employer(null, "Maria", "Kot", Arrays.asList(employee2));
        employee1.setEmployers(Arrays.asList(employer1,employer2));
        employee2.setEmployers(Arrays.asList(employer2));

        session.save(employee1);
        session.save(employee2);
        session.save(employer1);
        session.save(employer2);

        Employer employerFromDb = session.get(Employer.class, 2L);

        System.out.println(employerFromDb.getName() + " " + employerFromDb.getSurname());

        employerFromDb.getEmployees().stream()
                .forEach(employee -> System.out.println(employee.getName() + " " + employee.getSurname()));

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
