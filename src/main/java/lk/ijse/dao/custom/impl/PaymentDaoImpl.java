package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.PaymentDao;
import lk.ijse.entity.Payment;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public boolean add(Payment entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewID() throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1");
        String payId = (String) query.uniqueResult();
        session.close();
        return splitRegistrationId(payId);
    }

    private String splitRegistrationId(String payId) {
        if(payId != null) {
            String[] strings = payId.split("P0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "P00"+id;
            }else {
                if (length < 3){
                    return "P0"+id;
                }else {
                    return "P"+id;
                }
            }
        }
        return "P001";
    }

    @Override
    public List<Payment> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Payment ");
        List<Payment> payments = (ArrayList<Payment>) query.list();

        transaction.commit();
        session.close();
        return payments;
    }

    @Override
    public boolean update(Payment entity) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String id) throws IOException {
        return false;
    }

    @Override
    public Payment search(String userName) throws IOException {
        return null;
    }


    @Override
    public double getBalance(double upfrontPayment, double v, double programFee) {
        return programFee - (upfrontPayment + v);
    }

    @Override
    public String getStatus(double newBalance, double programFee) {
        if (newBalance == programFee) {
            return "Completed";
        }
        return "Incomplete";
    }

    @Override
    public double getPreviousInstallments(String registrationId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT balance from Payment where registration.registrationId = :registrationId");
        query.setParameter("registrationId", registrationId);
        ArrayList<Double> balanceList = (ArrayList<Double>) query.list();

        double Balance = 0;

        for (double balance : balanceList) {
            Balance += balance;
        }
        transaction.commit();
        session.close();
        return Balance;
    }

    @Override
    public double getNewBalance(double balance, double amount) {
        return balance - amount;
    }
}
