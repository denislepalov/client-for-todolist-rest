package lepdv.clientfortodolistrest.requests.admin;

import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.dto.TaskListDto;
import lepdv.clientfortodolistrest.util.Jwt;

import java.util.List;

import static lepdv.clientfortodolistrest.util.AdminRequests.getAllTasks;


public class GetAllTasksWithCustomParams {

    public static void main(String[] args) {

        final String jwt = Jwt.getAdminJwt();

        TaskListDto taskList = getAllTasks(jwt);
        List<ResponseTaskDto> allTasks = taskList.getTaskList();
        System.out.println(allTasks);

    }
}
