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
import java.util.ArrayList;
import java.util.List;

public class RegistrationBoImpl implements RegistrationBo {

    RegistrationDao registrationDao = (RegistrationDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    PaymentDao paymentDao = (PaymentDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public String generateNextRegistrationId() throws IOException {
        return registrationDao.generateNewID();

    }

    @Override
    public boolean saveStudentRegistration(RegistrationDTO registrationDTO) throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Stuck");

        Registration registration = new Registration(registrationDTO.getRegistrationId(), registrationDTO.getDate(), registrationDTO.getUpfrontPayment(), registrationDTO.getStudent(), registrationDTO.getCourse());
        boolean isSaved = registrationDao.add(registration);
        if (isSaved) {
            double newBalance = paymentDao.getBalance(registrationDTO.getUpfrontPayment(), 0.0, registrationDTO.getCourse().getProgramFee());
            String status = paymentDao.getStatus(newBalance, registrationDTO.getCourse().getProgramFee());
            Payment payment = new Payment(paymentDao.generateNewID(), 0.0, newBalance, registrationDTO.getDate(), status, registration);
            System.out.println("Payment" + payment);
            boolean isCompleted = paymentDao.add(payment);
            transaction.commit();
            if (isCompleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Transaction Completed...!").show();
                return true;
            }
        }
        transaction.rollback();
        session.close();
        return false;
    }

    @Override
    public boolean studentRegistration(RegistrationDTO registrationDTO) {
        System.out.println("call the student register");

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("session Not Run");

        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<RegistrationDTO> getAllRegistrations() throws IOException {
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        List<Registration> registrations = registrationDao.getAll();

        for (Registration registration : registrations) {
            RegistrationDTO registrationDTO = new RegistrationDTO(registration.getRegistrationId(), registration.getDate(), registration.getUpfrontPayment(), registration.getStudent(), registration.getCourse());
            registrationDTOS.add(registrationDTO);
        }
        return registrationDTOS;
    }

    @Override
    public String getStudentId(String registrationId) {
        return registrationDao.getStudentId(registrationId);
    }

    @Override
    public String getProgramName(String registrationId) {
        return registrationDao.getProgramName(registrationId);
    }

    @Override
    public double getProgramFee(String registrationId) {
        return registrationDao.getProgramFee(registrationId);
    }

    @Override
    public double getUpfrontPayment(String registrationId) {
        return registrationDao.getUpfrontPayment(registrationId);
    }

    @Override
    public RegistrationDTO getRegistrationDetail(String registrationId) {
        Registration registration = registrationDao.getRegistrationDetail(registrationId);
        return new RegistrationDTO(registration.getRegistrationId(), registration.getDate(), registration.getUpfrontPayment(), registration.getStudent(), registration.getCourse());

    }

}
