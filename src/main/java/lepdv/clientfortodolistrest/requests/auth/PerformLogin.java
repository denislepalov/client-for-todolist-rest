package lepdv.clientfortodolistrest.requests.auth;
import lepdv.clientfortodolistrest.dto.CredentialsDto;
import lepdv.clientfortodolistrest.dto.JwtDto;
import lepdv.clientfortodolistrest.util.AuthRequests;

public class PerformLogin {

    public static void main(String[] args) {

        CredentialsDto userCredentials = new CredentialsDto("Ivan", "Ivan");
        JwtDto response = AuthRequests.performLogin(userCredentials);
        String jwt = response.getJwt();
        System.out.println(response);
        System.out.println(jwt);

    }


}
