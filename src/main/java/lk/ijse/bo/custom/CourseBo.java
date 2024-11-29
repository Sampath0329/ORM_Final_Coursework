package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.CourseDTO;

import java.io.IOException;
import java.util.List;

public interface CourseBo extends SuperBo {
    String generateNextCourseId() throws IOException;

    boolean saveCourse(CourseDTO courseDTO) throws IOException;

    List<CourseDTO> getAllCourse() throws IOException;

    boolean updateCourse(CourseDTO courseDTO) throws IOException;

    boolean deleteCourse(String courseId) throws IOException;

    String getCourseName(String courseId);

    CourseDTO getCourseDetail(String courseId);

    int getCourseCount() throws IOException;
}
