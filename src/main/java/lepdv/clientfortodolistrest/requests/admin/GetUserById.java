package lepdv.clientfortodolistrest.requests.admin;

import lepdv.clientfortodolistrest.dto.UserForAdminDto;
import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.AdminRequests.getUserById;


public class GetUserById {

    public static void main(String[] args) {

        final String jwt = Jwt.getAdminJwt();

        UserForAdminDto userById = getUserById(jwt, 2L);
        System.out.println(userById);

    }
}
