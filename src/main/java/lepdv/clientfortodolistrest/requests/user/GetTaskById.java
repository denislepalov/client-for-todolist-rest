package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.UserRequests.getTaskById;

public class GetTaskById {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        ResponseTaskDto taskById = getTaskById(jwt, 1L);
        System.out.println(taskById);

    }
}
