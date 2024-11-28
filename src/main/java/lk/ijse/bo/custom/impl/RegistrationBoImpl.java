package lk.ijse.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.custom.RegistrationBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDao;
import lk.ijse.dao.custom.RegistrationDao;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Registration;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class RegistrationBoImpl implements RegistrationBo {

    RegistrationDao registrationDao = (RegistrationDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    PaymentDao paymentDao = (PaymentDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);


    @Override
    public String generateNextRegistrationId() throws IOException {
        return registrationDao.generateNewID();

    }

    @Override
    public boolean saveStudentRegistration(RegistrationDTO registrationDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Registration registration = new Registration(registrationDTO.getRegistrationId(), registrationDTO.getDate(), registrationDTO.getUpfrontPayment(), registrationDTO.getStudent(), registrationDTO.getCourse());
        boolean isSaved = false;
        try {
            isSaved = registrationDao.add(registration);
            if (isSaved) {
//                double newBalance = paymentDao.getBalance(registrationDTO.getUpfrontPayment(), 0.0, registrationDTO.getCourse().getProgramFee());
//                String status = paymentDao.getStatus(newBalance, registrationDTO.getCourse().getProgramFee());
//                Payment payment = new Payment(paymentDao.generateNewID(), 0.0, newBalance, registrationDTO.getDate(), status, registration);
//                System.out.println("Payment" + payment);
//                boolean isCompleted = paymentDao.add(payment);
//                transaction.commit();
//                if (isCompleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Transaction Completed...!").show();
                    return true;
//                }
            }
            transaction.rollback();
            session.close();
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        return false;

    }
}
