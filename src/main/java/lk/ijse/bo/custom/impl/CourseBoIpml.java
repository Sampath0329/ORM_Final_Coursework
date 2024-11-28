package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CourseBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CourseDao;
import lk.ijse.dto.CourseDTO;
import lk.ijse.entity.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseBoIpml implements CourseBo {
    CourseDao courseDao = (CourseDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public String generateNextCourseId() throws IOException {
        return courseDao.generateNewID();
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws IOException {
        return courseDao.add(new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getProgramFee()));

    }

    @Override
    public List<CourseDTO> getAllCourse() throws IOException {
        List<CourseDTO> courseDTOS = new ArrayList<>();

        List<Course> courses = courseDao.getAll();

        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getCourseId(), course.getCourseName(), course.getDuration(), course.getProgramFee());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws IOException {
        return courseDao.update(new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getProgramFee()));

    }

    @Override
    public boolean deleteCourse(String courseId) throws IOException {
        return courseDao.delete(courseId);
    }

    @Override
    public String getCourseName(String courseId) {
        return courseDao.getCourseName(courseId);
    }

    @Override
    public CourseDTO getCourseDetail(String courseId) {
        Course course = courseDao.getCourseDetail(courseId);
        CourseDTO courseDTO = new CourseDTO(course.getCourseId(), course.getCourseName(), course.getDuration(), course.getProgramFee());
        return courseDTO;
    }
}
