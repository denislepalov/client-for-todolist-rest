package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.UserRequests.deleteTaskById;

public class DeleteTaskById {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        String response = deleteTaskById(jwt, 1L);
        System.out.println(response);

    }
}
