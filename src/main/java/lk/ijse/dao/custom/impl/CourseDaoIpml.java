package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CourseDao;
import lk.ijse.entity.Course;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoIpml implements CourseDao {

    @Override
    public boolean add(Course entity) throws IOException {
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

        Query query = session.createQuery("SELECT CourseId FROM Course ORDER BY CourseId DESC LIMIT 1");
        String courseId = (String) query.uniqueResult();
        System.out.println(courseId);
        session.close();
        return splitStudentId(courseId);
    }

    @Override
    public List<Course> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Course");
        ArrayList<Course> courses = (ArrayList<Course>) query.list();

        transaction.commit();
        session.close();
        return courses;
    }

    @Override
    public boolean update(Course entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    private String splitStudentId(String courseId) {
        if(courseId != null) {
            String[] strings = courseId.split("CA10");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "CA100"+id;
            }else {
                if (length < 3){
                    return "CA10"+id;
                }else {
                    return "CA1"+id;
                }
            }
        }
        return "CA1001";
    }





    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Course WHERE CourseId = :CourseId");
        query.setParameter("CourseId", id);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Course search(String userName) throws IOException {
        return null;
    }

    @Override
    public String getCourseName(String courseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT CourseName from Course where CourseId = :courseId");
        query.setParameter("courseId", courseId);
        String courseName = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return courseName;
    }

    @Override
    public Course getCourseDetail(String courseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Course WHERE CourseId = :CourseId");
        query.setParameter("CourseId", courseId);

        List<Course> courses = query.list();

        for (Course course : courses) {
            String id = course.getCourseId();
            String courseName = course.getCourseName();
            String duration = course.getDuration();
            double programFee = course.getProgramFee();

            Course course1 = new Course(id, courseName, duration, programFee);
            transaction.commit();
            session.close();
            return course1;
        }

        transaction.commit();
        session.close();
        return null;
    }
}
