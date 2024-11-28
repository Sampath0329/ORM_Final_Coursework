package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface UserBo extends SuperBo {

    boolean addUser(UserDTO userDTO) throws IOException;

    List<UserDTO> getAllUsers() throws IOException;

    boolean updateUser(UserDTO userDTO) throws IOException;

    boolean deleteUser(String userName) throws IOException;

    UserDTO searchUser(String userName) throws IOException;
}
