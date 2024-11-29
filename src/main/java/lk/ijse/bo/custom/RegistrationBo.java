package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.RegistrationDTO;

import java.io.IOException;
import java.util.List;

public interface RegistrationBo extends SuperBo {
    String generateNextRegistrationId() throws IOException;

    boolean saveStudentRegistration(RegistrationDTO registrationDTO) throws IOException;

    boolean studentRegistration(RegistrationDTO registrationDTO);

    List<RegistrationDTO> getAllRegistrations() throws IOException;

    String getStudentId(String registrationId);

    String getProgramName(String registrationId);

    double getProgramFee(String registrationId);

    double getUpfrontPayment(String registrationId);

    RegistrationDTO getRegistrationDetail(String registrationId);
}
