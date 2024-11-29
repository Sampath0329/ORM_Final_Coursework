package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.PaymentDTO;

import java.io.IOException;
import java.util.List;

public interface PaymentBo extends SuperBo {

    String generateNextPaymentId() throws IOException;

    List<PaymentDTO> getAllPayments() throws IOException;

    double getPreviousInstallments(String registrationId);

    String getStatus(double newBalance, double programFee);

    boolean savePayment(PaymentDTO paymentDTO) throws IOException;

    double getNewBalance(double balance, double amount);
}
