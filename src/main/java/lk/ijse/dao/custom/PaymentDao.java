package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Payment;

public interface PaymentDao extends CrudDao<Payment> {

    double getBalance(double upfrontPayment, double v, double programFee);

    String getStatus(double newBalance, double programFee);

    double getPreviousInstallments(String registrationId);

    double getNewBalance(double balance, double amount);
}
