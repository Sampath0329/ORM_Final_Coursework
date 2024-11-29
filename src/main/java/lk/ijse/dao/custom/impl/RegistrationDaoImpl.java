package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.RegistrationDao;
import lk.ijse.entity.Course;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {
    @Override
    public boolean add(Registration entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT registrationId FROM Registration ORDER BY registrationId DESC LIMIT 1");
        String registrationId = (String) query.uniqueResult();
        session.close();
        return splitStudentId(registrationId);
    }

    private String splitStudentId(String registrationId) {
        if(registrationId != null) {
            String[] strings = registrationId.split("R0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "R00"+id;
            }else {
                if (length < 3){
                    return "R0"+id;
                }else {
                    return "R"+id;
                }
            }
        }
        return "R001";
    }

    @Override
    public List<Registration> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Registration");
        List<Registration> registrations = (ArrayList<Registration>) query.list();

        transaction.commit();
        session.close();
        return registrations;
    }

    @Override
    public boolean update(Registration entity) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String id) throws IOException {
        return false;
    }

    @Override
    public Registration search(String userName) throws IOException {
        return null;
    }

    @Override
    public String getStudentId(String registrationId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT student FROM Registration WHERE registrationId =: registrationId");
        query.setParameter("registrationId", registrationId);

        Student student = (Student) query.uniqueResult();
        transaction.commit();
        session.close();
        return student.getStudentId();
    }

    @Override
    public String getProgramName(String registrationId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT course from Registration where registrationId = :registrationId");
        query.setParameter("registrationId", registrationId);
        Course course = (Course) query.uniqueResult();

        transaction.commit();
        session.close();

        return course.getCourseName();
    }

    @Override
    public double getProgramFee(String registrationId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT course from Registration where registrationId = :registrationId");
        query.setParameter("registrationId", registrationId);
        Course course = (Course) query.uniqueResult();

        transaction.commit();
        session.close();

        return course.getProgramFee();
    }

    @Override
    public double getUpfrontPayment(String registrationId) {
        Session session = FactoryConfiguration.getInstance().getSession();

        try {
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("SELECT upfrontPayment from Registration where registrationId = :registrationId");
            query.setParameter("registrationId", registrationId);

            transaction.commit();
            return (double) query.uniqueResult();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public Registration getRegistrationDetail(String registrationId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Registration WHERE registrationId =: registrationId");
            query.setParameter("registrationId", registrationId);

            transaction.commit();
            return (Registration) query.uniqueResult();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
}
