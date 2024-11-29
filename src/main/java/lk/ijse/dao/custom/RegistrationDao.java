package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Registration;

public interface RegistrationDao extends CrudDao<Registration> {
    String getStudentId(String registrationId);

    String getProgramName(String registrationId);

    double getProgramFee(String registrationId);

    double getUpfrontPayment(String registrationId);

    Registration getRegistrationDetail(String registrationId);
}
