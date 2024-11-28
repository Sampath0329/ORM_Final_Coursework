package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {

    UserDao userDao = (UserDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean addUser(UserDTO userDTO) throws IOException {
        User user = new User(
                userDTO.getUserName(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getNic(),
                userDTO.getAddress(),
                userDTO.getContact(),
                userDTO.getMail(),
                userDTO.getPw()
        );
        return userDao.add(user);
    }

    @Override
    public List<UserDTO> getAllUsers() throws IOException {
        List<User> all= userDao.getAll();
        List<UserDTO> allUsers = new ArrayList<>();
        for (User u : all){
            allUsers.add(
                    new UserDTO(
                            u.getUserName(),
                            u.getFirstName(),
                            u.getLastName(),
                            u.getNic(),
                            u.getAddress(),
                            u.getContact(),
                            u.getMail(),
                            u.getPw()
                    )
            );
        }
        return allUsers;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws IOException {
        User user = new User(
                userDTO.getUserName(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getNic(),
                userDTO.getAddress(),
                userDTO.getContact(),
                userDTO.getMail(),
                userDTO.getPw()
        );



        return userDao.update(user);
    }

    @Override
    public boolean deleteUser(String userName) throws IOException {
        return userDao.delete(userName);
    }


    @Override
    public UserDTO searchUser(String userName) throws IOException {
        User user = userDao.search(userName);

        return new UserDTO(
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getNic(),
                user.getAddress(),
                user.getContact(),
                user.getMail(),
                user.getPw()
        );
    }
}
