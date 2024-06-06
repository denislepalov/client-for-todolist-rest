package lepdv.clientfortodolistrest.requests.admin;

import lepdv.clientfortodolistrest.util.Jwt;
import static lepdv.clientfortodolistrest.util.AdminRequests.unlockUserById;


public class UnlockUserById {

    public static void main(String[] args) {

        final String jwt = Jwt.getAdminJwt();

        String response = unlockUserById(jwt, 2L);
        System.out.println(response);

    }
}
