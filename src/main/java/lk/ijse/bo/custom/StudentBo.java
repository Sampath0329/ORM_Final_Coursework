package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.StudentDTO;

import java.io.IOException;
import java.util.List;

public interface StudentBo extends SuperBo {
    String generateNextStudentId() throws IOException;

    List<StudentDTO> getAllStudents() throws IOException;

    boolean saveStudent(StudentDTO studentDTO) throws IOException;

    boolean updateStudent(StudentDTO studentDTO) throws IOException;

    boolean deleteStudent(String studentId) throws IOException;

    String getStudentName(String studentId) throws IOException;

    StudentDTO getStudentDetail(String studentId);

    int getStudentCount() throws IOException;
}
