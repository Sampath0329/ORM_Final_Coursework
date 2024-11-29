package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDao;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {

    StudentDao studentDao = (StudentDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public String generateNextStudentId() throws IOException {
        return studentDao.generateNewID();
    }

    @Override
    public List<StudentDTO> getAllStudents() throws IOException {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> students = studentDao.getAll();

        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getStudentId(), student.getName(), student.getContact(), student.getAddress(), student.getEmail());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws IOException {
        return studentDao.add(new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getAddress(), studentDTO.getEmail()));

    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws IOException {
        return studentDao.update(new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getAddress(), studentDTO.getEmail()));

    }

    @Override
    public boolean deleteStudent(String studentId) throws IOException {
        return studentDao.delete(studentId);

    }

    @Override
    public String getStudentName(String studentId) throws IOException {
        return studentDao.getStudentName(studentId);
    }

    @Override
    public StudentDTO getStudentDetail(String studentId) {
        Student student = studentDao.getStudentDetail(studentId);
        StudentDTO studentDTO = new StudentDTO(student.getStudentId(), student.getName(), student.getContact(), student.getAddress(), student.getEmail());
        return studentDTO;
    }

    @Override
    public int getStudentCount() throws IOException {
        List<Student> all = studentDao.getAll();
        int studentCount = 0;
        for (Student student : all){
            studentCount++;
        }
        return studentCount;
    }
}
