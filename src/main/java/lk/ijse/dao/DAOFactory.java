package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER,COURSE,STUDENT,REGISTRATION,PAYMENT
    }
    public SuperDao getDAO(DAOTypes daoTypes) {
        switch (daoTypes){
            case USER:
                return new UserDaoImpl();
            case COURSE:
                return new CourseDaoIpml();
            case STUDENT:
                return new StudetnDaoImpl();
            case REGISTRATION:
                return new RegistrationDaoImpl();
            case PAYMENT:
                return new PaymentDaoImpl();
            default:
                return null;
        }
    }

    }
