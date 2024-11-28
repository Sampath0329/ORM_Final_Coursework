package lk.ijse.dto;

import lk.ijse.entity.Registration;
import lombok.*;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {
    private String paymentId;
    private double amount;
    private double balance;
    private Date date;
    private String status;
    private Registration registration;
}
