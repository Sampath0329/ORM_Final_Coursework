package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.StudentDao;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudetnDaoImpl implements StudentDao {
    @Override
    public boolean add(Student entity) throws IOException {
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
//        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT studentId FROM Student ORDER BY studentId DESC LIMIT 1");
        String studentId = (String) query.uniqueResult();
//        System.out.println(studentId);
//        transaction.commit();
        session.close();
        return splitStudentId(studentId);
    }

    private String splitStudentId(String studentId) {
        if(studentId != null) {
            String[] strings = studentId.split("S0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "S00"+id;
            }else {
                if (length < 3){
                    return "S0"+id;
                }else {
                    return "S"+id;
                }
            }
        }
        return "S001";
    }

    @Override
    public List<Student> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Student");
        ArrayList<Student> students = (ArrayList<Student>) query.list();

        transaction.commit();
        session.close();
        return students;
    }

    @Override
    public boolean update(Student entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Student WHERE studentId = :studentId");
        query.setParameter("studentId", id);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String userName) throws IOException {
        return null;
    }

    @Override
    public String getStudentName(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT name from Student where studentId = :studentId");
        query.setParameter("studentId", studentId);
        String studentName = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return studentName;
    }

    @Override
    public Student getStudentDetail(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Student where studentId = :studentId");
        query.setParameter("studentId", studentId);

        List<Student> students = query.list();

        for (Student student : students) {
            String studentId1 = student.getStudentId();
            String name = student.getName();
            String address = student.getAddress();
            String contact = student.getContact();
            String email = student.getEmail();

            Student student1 = new Student(studentId1, name, contact, address, email);
            transaction.commit();
            session.close();
            return student1;
        }
        return null;
    }
}
