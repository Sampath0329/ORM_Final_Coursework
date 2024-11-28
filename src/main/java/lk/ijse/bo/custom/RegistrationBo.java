package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.RegistrationDTO;

import java.io.IOException;

public interface RegistrationBo extends SuperBo {
    String generateNextRegistrationId() throws IOException;

    boolean saveStudentRegistration(RegistrationDTO registrationDTO);
}
