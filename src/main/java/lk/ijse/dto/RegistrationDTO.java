package lk.ijse.dto;

import lk.ijse.entity.Course;
import lk.ijse.entity.Student;
import lombok.*;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationDTO {
    private String registrationId;
    private Date date;
    private double upfrontPayment;
    private Student student;
    private Course course;
}
