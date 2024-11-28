package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;

public interface PaymentBo extends SuperBo {
    double getBalance(double upfrontPayment, double amount, double programFee);
}
