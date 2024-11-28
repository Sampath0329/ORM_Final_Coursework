package lk.ijse.dto.Tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationTm {
    private String registrationId;
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private double upfrontPayment;
}
