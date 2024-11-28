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
        USER
    }

    public SuperBo getBo(BoTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBoImpl();

            default:
                return null;
        }
    }
}
