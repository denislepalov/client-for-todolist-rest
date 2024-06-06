package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.dto.CreateTaskDto;
import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.util.Jwt;

import java.time.LocalDate;
import java.time.Month;

import static lepdv.clientfortodolistrest.util.UserRequests.createTask;

public class CreateTask {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        CreateTaskDto newTask = CreateTaskDto.builder()
                .description("Ivan task4")
                .dueDate(LocalDate.of(2025, Month.MAY, 11))
                .build();
        ResponseTaskDto response = createTask(jwt, newTask);
        System.out.println(response);

    }
}
