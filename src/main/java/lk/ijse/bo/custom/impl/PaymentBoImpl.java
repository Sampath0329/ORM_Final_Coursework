package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDao;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBo {

    PaymentDao paymentDao = (PaymentDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public String generateNextPaymentId() throws IOException {
        return paymentDao.generateNewID();
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws IOException {
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        List<Payment> payments = paymentDao.getAll();

        for (Payment payment : payments) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPaymentId(),payment.getAmount(),payment.getBalance(),payment.getDate(),payment.getStatus(),payment.getRegistration());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }

    @Override
    public double getPreviousInstallments(String registrationId) {
        return paymentDao.getPreviousInstallments(registrationId);
    }

    @Override
    public String getStatus(double newBalance, double programFee) {
        return paymentDao.getStatus(newBalance, programFee);
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws IOException {
        return paymentDao.add(new Payment(paymentDTO.getPaymentId(), paymentDTO.getAmount(), paymentDTO.getBalance(), paymentDTO.getDate(), paymentDTO.getStatus(), paymentDTO.getRegistration()));

    }

    @Override
    public double getNewBalance(double balance, double amount) {
        return paymentDao.getNewBalance(balance, amount);
    }
}
