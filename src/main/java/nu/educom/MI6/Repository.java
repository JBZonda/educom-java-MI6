package nu.educom.MI6;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.persistence.*;
public class Repository {
    Session session;
    SessionFactory factory;
    Connection conn;
    Repository(){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        factory = meta.getSessionFactoryBuilder().build();
    }
    private void connect(){
        session = factory.openSession();
    }

    private void disconnect(){
        session.close();
    }

    public List<Agent> getAgents(int serviceNumber) {
        return getAgents(serviceNumber, true);
    }

    public List<Agent> getAgents(int serviceNumber, boolean active){
        List<Agent> agents;
        try{
            connect();
            String hql = "FROM Agent AS a WHERE a.serviceNumber = :serviceNumber and a.active= :active";
            var query = session.createQuery(hql);
            query.setParameter("serviceNumber",serviceNumber);
            query.setParameter("active", active);
            agents = (List<Agent>) query.list();

        } finally {
            disconnect();
        }

        return agents;
    }

    public Agent getAgent(int serviceNumber) {
        return getAgent(serviceNumber, true);
    }
    public Agent getAgent(int serviceNumber, boolean active){
        List<Agent> result = getAgents(serviceNumber, active);
        if (result.size() != 1){
            return null;
        }
        return result.get(0);
    }

    public boolean authenticateAgent(int sN,String pP){
        Agent agent = getAgent(sN);
        if (agent==null){
            return false;
        }
        return agent.getPassPhrase().equals(pP);
    }

    public List<LogInAttempt> getLastLogInAttempts(int serviceNumber) {
        return getLastLogInAttempts(serviceNumber, false);
    }

    public List<LogInAttempt> getLastLogInAttempts(int serviceNumber, boolean onlyLastLogIn){

        List<LogInAttempt> lastLogInAttempts = new ArrayList<>();
        try{
            connect();
            String hql;
            hql = "FROM LogInAttempt WHERE serviceNumber = :serviceNumber ORDER BY loginTime DESC";

            var query = session.createQuery(hql);
            query.setParameter("serviceNumber",serviceNumber);
            if (onlyLastLogIn){
                query.setMaxResults(1);
            }

            lastLogInAttempts = (List<LogInAttempt>) query.list();


        } finally {
            disconnect();
        }
        return lastLogInAttempts;
    }

    public void createLogInAttempt(int serviceNumber, boolean success){
        try{
            connect();
            Transaction t = session.beginTransaction();

            LogInAttempt logInAttempt = new LogInAttempt();
            logInAttempt.setServiceNumber(serviceNumber);
            logInAttempt.setSuccess(success);
            logInAttempt.setLoginTime(LocalDateTime.now());

            session.save(logInAttempt);
            t.commit();

        } finally {
            disconnect();
        }

    }


}
