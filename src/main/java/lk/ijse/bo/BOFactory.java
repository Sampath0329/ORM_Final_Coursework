package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BoTypes{
        USER,COURSE,STUDENT,REGISTRATION,PAYMENT
    }

    public SuperBo getBo(BoTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBoImpl();
            case COURSE:
                return new CourseBoIpml();
            case STUDENT:
                return new StudentBoImpl();
            case REGISTRATION:
                return new RegistrationBoImpl();
            case PAYMENT:
                return new PaymentBoImpl();
            default:
                return null;
        }
    }
}
