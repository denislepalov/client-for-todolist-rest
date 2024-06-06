package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.dto.CredentialsDto;
import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.UserRequests.deleteAccount;

public class DeleteAccount {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        CredentialsDto userCredentials = new CredentialsDto("Ivan", "Ivan");
        String response = deleteAccount(jwt, userCredentials);
        System.out.println(response);

    }
}
