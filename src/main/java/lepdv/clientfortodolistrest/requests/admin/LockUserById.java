package lepdv.clientfortodolistrest.requests.admin;

import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.AdminRequests.lockUserById;


public class LockUserById {

    public static void main(String[] args) {

        final String jwt = Jwt.getAdminJwt();

        String response = lockUserById(jwt, 2L);
        System.out.println(response);

    }
}
