package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.dto.UpdateTaskDto;
import lepdv.clientfortodolistrest.util.Jwt;

import java.time.LocalDate;
import java.time.Month;

import static lepdv.clientfortodolistrest.util.UserRequests.updateTaskById;

public class UpdateTaskById {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        UpdateTaskDto updatingTask = UpdateTaskDto.builder()
                .description("New description")
                .dueDate(LocalDate.of(2025, Month.MAY, 11))
                .build();
        ResponseTaskDto response = updateTaskById(jwt, updatingTask, 1L);
        System.out.println(response);

    }
}
