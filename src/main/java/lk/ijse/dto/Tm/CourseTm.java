package lk.ijse.dto.Tm;

import lombok.*;



@ToString
public class CourseTm {
    private String CourseId;
    private String CourseName;
    private String Duration;
    private double programFee;

    public CourseTm() {
    }

    public CourseTm(String courseId, String courseName, String duration, double programFee) {
        CourseId = courseId;
        CourseName = courseName;
        Duration = duration;
        this.programFee = programFee;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public double getProgramFee() {
        return programFee;
    }

    public void setProgramFee(double programFee) {
        this.programFee = programFee;
    }
}
