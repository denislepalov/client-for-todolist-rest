package lepdv.clientfortodolistrest.util;

import lepdv.clientfortodolistrest.dto.*;
import lombok.experimental.UtilityClass;

import static lepdv.clientfortodolistrest.util.RequestMethods.*;


@UtilityClass
public class AdminRequests {

    static final String baseUrl = "http://localhost:8080/api/v2/admin";



    public static UserListDto getAllUsers(String jwt) {
        String getAllUsersUrl = baseUrl + "/users";
        return makeGetRequest(jwt, getAllUsersUrl, UserListDto.class);
    }
    public static UserListDto getAllUsers(String jwt, int page, int size) {
        String getAllUsersUrl = baseUrl + "/users?page=" + page + "&size=" + size;
        return makeGetRequest(jwt, getAllUsersUrl, UserListDto.class);
    }



    public static UserForAdminDto getUserById(String jwt, Long id) {
        String getUserByIdUrl = baseUrl + "/users/" + id;
        return makeGetRequest(jwt, getUserByIdUrl, UserForAdminDto.class);
    }



    public static String lockUserById(String jwt, Long id) {
        String lockUserUrl = baseUrl + "/users/" + id + "/lock";
        return makePutRequest(jwt, lockUserUrl, String.class);
    }



    public static String unlockUserById(String jwt, Long id) {
        String unlockUserUrl = baseUrl + "/users/" + id + "/unlock";
        return makePutRequest(jwt, unlockUserUrl, String.class);
    }



    public static String deleteUserById(String jwt, Long id, CredentialsDto credentials) {
        String deleteAccountUrl = baseUrl + "/users/" + id + "/delete";
        return makeDeleteRequest(jwt, deleteAccountUrl, credentials, String.class);
    }



    public static TaskListDto getAllTasks(String jwt) {
        String getAllTasksUrl = baseUrl + "/tasks";
        return makeGetRequest(jwt, getAllTasksUrl, TaskListDto.class);
    }
    public static TaskListDto getAllTasks(String jwt, int page, int size) {
        String urlGetAllTasks = baseUrl + "/tasks?page=" + page + "&size=" + size;
        return makeGetRequest(jwt, urlGetAllTasks, TaskListDto.class);
    }



    public static ResponseTaskDto getTaskById(String jwt, Long id) {
        String getTaskByIdUrl = baseUrl + "/tasks/" + id;
        return makeGetRequest(jwt, getTaskByIdUrl, ResponseTaskDto.class);
    }



}
