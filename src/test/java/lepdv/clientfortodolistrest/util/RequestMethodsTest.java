package lepdv.clientfortodolistrest.util;

import lepdv.clientfortodolistrest.dto.CredentialsDto;
import lepdv.clientfortodolistrest.dto.JwtDto;
import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static lepdv.clientfortodolistrest.Constants.*;
import static lepdv.clientfortodolistrest.util.UserRequests.createTask;
import static lepdv.clientfortodolistrest.util.UserRequests.deleteTaskById;
import static lepdv.clientfortodolistrest.util.Jwt.getUserJwt;
import static lepdv.clientfortodolistrest.util.RequestMethods.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.StringUtils.hasText;


class RequestMethodsTest {



    @Test
    void test_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<RequestMethods> constructor = RequestMethods.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }




    @Test
    void makeGetRequest_shouldGetValidResponse_whenRequestIsValid() {
        final String userJwt = getUserJwt();
        final String getUserUrl = "http://localhost:8080/api/v2/user";
        final Class<UserDto> responseType = UserDto.class;

        UserDto actualResult = makeGetRequest(userJwt, getUserUrl, responseType);

        assertEquals(USER_DTO, actualResult);
    }

    @Test
    void makeGetRequest_shouldThrowException_whenRequestIsInvalid() {
        final String userJwt = "dummy";
        final String getUserUrl = "http://localhost:8080/api/v2/user";
        final Class<UserDto> responseType = UserDto.class;

        assertThrows(RuntimeException.class, () -> makeGetRequest(userJwt, getUserUrl, responseType));
    }




    @Test
    void makePostRequest1_shouldGetValidResponse_whenRequestIsValid() {
        final String performLoginUrl = "http://localhost:8080/api/v2/authenticate/login";
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Ivan")
                .password("Ivan")
                .build();
        final Class<JwtDto> responseType = JwtDto.class;

        JwtDto actualResult = makePostRequest(performLoginUrl, credentialsDto, responseType);

        assertTrue(hasText(actualResult.getJwt()));
    }

    @Test
    void makePostRequest1_shouldThrowException_whenRequestIsInvalid() {
        final String performLoginUrl = "http://localhost:8080/api/v2/authenticate/login";
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Ivan")
                .password("dummy")
                .build();
        final Class<JwtDto> responseType = JwtDto.class;

        assertThrows(RuntimeException.class, () -> makePostRequest(performLoginUrl, credentialsDto, responseType));
    }


    @Test
    void makePostRequest2_shouldGetValidResponse_whenRequestIsValid() {
        final String userJwt = getUserJwt();
        final String createTaskUrl = "http://localhost:8080/api/v2/tasks";
        final Class<ResponseTaskDto> responseType = ResponseTaskDto.class;

        ResponseTaskDto actualResult = makePostRequest(userJwt, createTaskUrl, CREATE_TASK_DTO, responseType);

        assertNotNull(actualResult.getId());
        assertEquals(CREATE_TASK_DTO.getDescription(), actualResult.getDescription());
        assertEquals(CREATE_TASK_DTO.getDueDate(), actualResult.getDueDate());
        deleteTaskById(userJwt, actualResult.getId());
    }

    @Test
    void makePostRequest2_shouldThrowException_whenRequestIsInvalid() {
        final String userJwt = "dummy";
        final String createTaskUrl = "http://localhost:8080/api/v2/tasks";
        final Class<ResponseTaskDto> responseType = ResponseTaskDto.class;

        assertThrows(RuntimeException.class, () -> makePostRequest(userJwt, createTaskUrl, CREATE_TASK_DTO, responseType));
    }




    @Test
    void makePutRequest1_shouldGetValidResponse_whenRequestIsValid() {
        final String userJwt = getUserJwt();
        final ResponseTaskDto createdTask = createTask(userJwt, CREATE_TASK_DTO);
        final String markAsCompletedTaskUrl = "http://localhost:8080/api/v2/tasks/" + createdTask.getId()
                + "/mark-as-completed";
        final Class<String> responseType = String.class;
        final String expectedResult = "Task with id=" + createdTask.getId() + " was marked as completed";

        String actualResult = makePutRequest(userJwt, markAsCompletedTaskUrl, responseType);

        assertEquals(expectedResult, actualResult);
        deleteTaskById(userJwt, createdTask.getId());
    }

    @Test
    void makePutRequest1_shouldThrowException_whenRequestIsInvalid() {
        final String userJwt = "dummy";
        final String markAsCompletedTaskUrl = "http://localhost:8080/api/v2/tasks/" + TASK.getId()
                + "/mark-as-completed";
        final Class<String> responseType = String.class;

        assertThrows(RuntimeException.class, () -> makePutRequest(userJwt, markAsCompletedTaskUrl, responseType));
    }


    @Test
    void makePutRequest2_shouldGetValidResponse_whenRequestIsValid() {
        final String userJwt = getUserJwt();
        final ResponseTaskDto createdTask = createTask(userJwt, CREATE_TASK_DTO);
        final String updateTaskUrl = "http://localhost:8080/api/v2/tasks/" + createdTask.getId();
        final Class<ResponseTaskDto> responseType = ResponseTaskDto.class;

        ResponseTaskDto actualResult = makePutRequest(userJwt, updateTaskUrl, UPDATE_TASK_DTO, responseType);

        assertEquals(createdTask.getId(), actualResult.getId());
        assertEquals(UPDATE_TASK_DTO.getDescription(), actualResult.getDescription());
        assertEquals(UPDATE_TASK_DTO.getDueDate(), actualResult.getDueDate());
        deleteTaskById(userJwt, createdTask.getId());
    }

    @Test
    void makePutRequest2_shouldThrowException_whenRequestIsInvalid() {
        final String userJwt = "dummy";
        final String updateTaskUrl = "http://localhost:8080/api/v2/tasks/" + TASK.getId();
        final Class<ResponseTaskDto> responseType = ResponseTaskDto.class;

        assertThrows(RuntimeException.class, () -> makePutRequest(userJwt, updateTaskUrl, UPDATE_TASK_DTO, responseType));
    }




    @Test
    void makeDeleteRequest_1_shouldGetValidResponse_whenRequestIsValid() {
        final String userJwt = getUserJwt();
        final ResponseTaskDto createdTask = createTask(userJwt, CREATE_TASK_DTO);
        final String deleteTaskUrl = "http://localhost:8080/api/v2/tasks/" + createdTask.getId();
        final Class<String> requestType = String.class;
        final String expectedResult = "Task with id=" + createdTask.getId() + " was deleted";

        String actualResult = makeDeleteRequest(userJwt, deleteTaskUrl, requestType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void makeDeleteRequest_1_shouldThrowException_whenRequestIsInvalid() {
        final String userJwt = "dummy";
        final String deleteTaskUrl = "http://localhost:8080/api/v2/tasks/" + TASK.getId();
        final Class<String> requestType = String.class;

        assertThrows(RuntimeException.class, () -> makeDeleteRequest(userJwt, deleteTaskUrl, requestType));
    }



    @Test
    void makeDeleteRequest_2_shouldGetValidResponse_whenRequestIsValid() {
        final String userJwt = AuthRequests.register(REGISTER_DTO).getJwt();
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Petr")
                .password("Petr")
                .build();
        final String deleteAccountUrl = "http://localhost:8080/api/v2/user/delete-account";
        final Class<String> requestType = String.class;
        final String expectedResult = "Account of %s was deleted".formatted(credentialsDto.getUsername());

        String actualResult = makeDeleteRequest(userJwt, deleteAccountUrl, credentialsDto, requestType);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void makeDeleteRequest_2_shouldThrowException_whenRequestIsInvalid() {
        final String userJwt = "dummy";
        final String deleteAccountUrl = "http://localhost:8080/api/v2/user/delete-account";
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Petr")
                .password("Petr")
                .build();
        final Class<String> requestType = String.class;

        assertThrows(RuntimeException.class, () -> makeDeleteRequest(userJwt, deleteAccountUrl,
                credentialsDto, requestType));
    }





}










