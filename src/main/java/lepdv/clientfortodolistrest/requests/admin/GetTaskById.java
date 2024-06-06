package lepdv.clientfortodolistrest.requests.admin;

import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.AdminRequests.getTaskById;


public class GetTaskById {

    public static void main(String[] args) {

        final String jwt = Jwt.getAdminJwt();

        ResponseTaskDto taskById = getTaskById(jwt, 5L);
        System.out.println(taskById);

    }
}
