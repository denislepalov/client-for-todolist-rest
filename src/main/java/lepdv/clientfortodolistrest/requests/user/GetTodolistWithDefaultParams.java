package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.dto.TaskListDto;
import lepdv.clientfortodolistrest.util.Jwt;

import java.util.List;

import static lepdv.clientfortodolistrest.util.UserRequests.getTodoList;

public class GetTodolistWithDefaultParams {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        TaskListDto taskList = getTodoList(jwt);
        List<ResponseTaskDto> todoList = taskList.getTaskList();
        System.out.println(todoList);

    }
}
