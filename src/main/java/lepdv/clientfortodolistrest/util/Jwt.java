package lepdv.clientfortodolistrest.util;

import lepdv.clientfortodolistrest.dto.CredentialsDto;
import lombok.experimental.UtilityClass;

import static lepdv.clientfortodolistrest.util.AuthRequests.performLogin;


@UtilityClass
public class Jwt {

    public static String getAdminJwt() {
        return performLogin(new CredentialsDto("Admin", "Admin")).getJwt();
    }

    public static String getUserJwt() {
        return performLogin(new CredentialsDto("Ivan", "Ivan")).getJwt();
    }

    public static String getKatyaJwt() {
        return performLogin(new CredentialsDto("Katya", "Katya")).getJwt();
    }


}
