package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.dto.UserDto;
import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.UserRequests.getUser;

public class GetUser {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        UserDto userDto = getUser(jwt);
        System.out.println(userDto);

    }
}
