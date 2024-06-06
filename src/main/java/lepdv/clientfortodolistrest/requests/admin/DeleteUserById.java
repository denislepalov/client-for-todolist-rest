package lepdv.clientfortodolistrest.requests.admin;

import lepdv.clientfortodolistrest.dto.CredentialsDto;
import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.AdminRequests.deleteUserById;


public class DeleteUserById {

    public static void main(String[] args) {

        final String jwt = Jwt.getAdminJwt();

        CredentialsDto adminCredentials = new CredentialsDto("Admin", "Admin");
        String response = deleteUserById(jwt,2L, adminCredentials);
        System.out.println(response);

    }
}
