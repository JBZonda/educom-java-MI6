package nu.educom.MI6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateTests {

    public void saveWithHibernate(){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        var query = session.createQuery("FROM Agent");
        List results = query.list();

        System.out.println("the result:");
        Agent r1 = (Agent) results.get(0);
        System.out.println(r1.getServiceNumber());
        System.out.println(results.get(1).getClass());
        factory.close();
        session.close();

    }
    public List readWithHibernate(String qry){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        var query = session.createQuery(qry);
        List results = query.list();

        factory.close();
        session.close();
        return results;
    }
}
