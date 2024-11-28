package lk.ijse.dao;

import lk.ijse.dao.custom.impl.UserDaoImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER
    }
    public SuperDao getDAO(DAOTypes daoTypes) {
        switch (daoTypes){
            case USER:
                return new UserDaoImpl();
            default:
                return null;
        }
    }

    }
