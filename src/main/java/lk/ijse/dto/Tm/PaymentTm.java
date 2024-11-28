package lk.ijse.dto.Tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentTm {
    private String registrationId;
    private String studentId;
    private String programName;
    private double payment;
    private double balance;
    private String status;
}
