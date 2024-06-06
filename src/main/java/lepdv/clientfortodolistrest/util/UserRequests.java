package lepdv.clientfortodolistrest.util;

import lepdv.clientfortodolistrest.dto.*;
import lombok.experimental.UtilityClass;

import static lepdv.clientfortodolistrest.util.RequestMethods.*;


@UtilityClass
public class UserRequests {


    static final String userBaseUrl = "http://localhost:8080/api/v2/user";
    static final String tasksBaseUrl = "http://localhost:8080/api/v2/tasks";





    public static UserDto getUser(String jwt) {
        return makeGetRequest(jwt, userBaseUrl, UserDto.class);
    }


    public static UserDto updateUser(String jwt, UserDto user) {
        return makePutRequest(jwt, userBaseUrl, user, UserDto.class);
    }


    public static String editPassword(String jwt, EditPasswordDto editPassword) {
        String editPasswordUrl = userBaseUrl + "/edit-password";
        return makePutRequest(jwt, editPasswordUrl, editPassword, String.class);
    }


    public static String deleteAccount(String jwt, CredentialsDto credentials) {
        String deleteAccountUrl = userBaseUrl + "/delete-account";
        return makeDeleteRequest(jwt, deleteAccountUrl, credentials, String.class);
    }


//-------------------------------------------------------------------------------------------------------------------


    public static ResponseTaskDto createTask(String jwt, CreateTaskDto newTask) {
        return makePostRequest(jwt, tasksBaseUrl, newTask, ResponseTaskDto.class);
    }


    public static TaskListDto getTodoList(String jwt) {
        String getTodoListUrl = tasksBaseUrl + "/todo-list";
        return makeGetRequest(jwt, getTodoListUrl, TaskListDto.class);
    }

    public static TaskListDto getTodoList(String jwt, int page, int size) {
        String getTodoListUrl = tasksBaseUrl + "/todo-list?page=" + page + "&size=" + size;
        return makeGetRequest(jwt, getTodoListUrl, TaskListDto.class);
    }



    public static ResponseTaskDto getTaskById(String jwt, Long id) {
        String getTaskUrl = tasksBaseUrl + "/" + id;
        return makeGetRequest(jwt, getTaskUrl, ResponseTaskDto.class);
    }


    public static ResponseTaskDto updateTaskById(String jwt, UpdateTaskDto task, Long id) {
        String updateTaskUrl = tasksBaseUrl + "/" + id;
        return makePutRequest(jwt, updateTaskUrl, task, ResponseTaskDto.class);
    }


    public static String markAsCompletedById(String jwt, Long id) {
        String markAsCompletedUrl = tasksBaseUrl + "/" + id + "/mark-as-completed";
        return makePutRequest(jwt, markAsCompletedUrl, String.class);
    }


    public static String deleteTaskById(String jwt, Long id) {
        String deleteTaskUrl = tasksBaseUrl + "/" + id;
        return makeDeleteRequest(jwt, deleteTaskUrl, String.class);
    }



}
