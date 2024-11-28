package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Course;

public interface CourseDao extends CrudDao<Course> {
    String getCourseName(String courseId);

    Course getCourseDetail(String courseId);
}
