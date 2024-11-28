package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Student;

public interface StudentDao extends CrudDao<Student> {
    String getStudentName(String studentId);

    Student getStudentDetail(String studentId);
}
