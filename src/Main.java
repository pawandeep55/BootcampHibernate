import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;

/**
 * Created by sandeep on 28/6/17.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        createData();
//        createData();
        queryData();
//        updateData();
//        queryData();
//        deleteData();
//        queryData();
        ourSessionFactory.close();
    }

    private static void createData() {
        Person malePersion = new PersonMale();
        malePersion.name = "Abc";
        malePersion.age = 23;

        Person femalePerson = new PersonFemale();
        femalePerson.name = "Xyz";
        femalePerson.age = 21;

        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(malePersion);
            session.save(femalePerson);
            session.getTransaction().commit();
        }
    }

    private static void updateData() {
        try (Session session = getSession()) {
            Person person = session.get(Person.class, 1);
            person.age = 25;
            session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
        }
    }

    private static void deleteData() {
        try (Session session = getSession()) {
            Person person = session.get(Person.class, 1);
            session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();
        }
    }

    private static void queryData() {
        try (Session session = getSession()) {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        }
    }
}