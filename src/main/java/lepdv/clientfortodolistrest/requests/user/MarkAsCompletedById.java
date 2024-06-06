package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.UserRequests.markAsCompletedById;

public class MarkAsCompletedById {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        String response = markAsCompletedById(jwt, 1L);
        System.out.println(response);

    }
}
