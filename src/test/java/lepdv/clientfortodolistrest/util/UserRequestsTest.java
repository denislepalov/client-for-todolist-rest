package lepdv.clientfortodolistrest.util;

import lepdv.clientfortodolistrest.dto.*;
import lepdv.clientfortodolistrest.mapper.Mapper;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static lepdv.clientfortodolistrest.Constants.*;
import static lepdv.clientfortodolistrest.util.Jwt.getUserJwt;
import static org.junit.jupiter.api.Assertions.*;


class UserRequestsTest {



    @Test
    void test_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<UserRequests> constructor = UserRequests.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }




    @Test
    void getUser_shouldGetUserDto_whenDataIsValid() {
        final String userJwt = getUserJwt();

        UserDto actualResult = UserRequests.getUser(userJwt);

        assertEquals(USER_DTO, actualResult);
    }

    @Test
    void getUser_shouldThrowException_whenDataIsInvalid() {
        final String userJwt = "dummy";

        assertThrows(RuntimeException.class, () -> UserRequests.getUser(userJwt));
    }




    @Test
    void updateUser_shouldGetUpdatedUserDto_whenDataIsValid() {
        final JwtDto createdJwtDto = AuthRequests.register(REGISTER_DTO);
        final String createdUserJwt = createdJwtDto.getJwt();
        final UserDto updatedUserDto = UserDto.builder()
                .username("Petr")
                .fullName("updated full name")
                .dateOfBirth(LocalDate.of(2005, 1, 1))
                .build();

        UserDto actualResult = UserRequests.updateUser(createdUserJwt, updatedUserDto);

        assertEquals(updatedUserDto, actualResult);
        UserRequests.deleteAccount(createdUserJwt, new CredentialsDto("Petr", "Petr"));
    }

    @Test
    void updateUser_shouldThrowException_whenDataIsInvalid() {
        final String userJwt = getUserJwt();
        final UserDto updatedUserDto = UserDto.builder()
                .username("P")
                .fullName("updated full name")
                .dateOfBirth(LocalDate.of(2100, 1, 1))
                .build();

        assertThrows(RuntimeException.class, () -> UserRequests.updateUser(userJwt, updatedUserDto));
    }




    @Test
    void editPassword_shouldGetEditPasswordString_whenDataIsValid() {
        final JwtDto createdJwtDto = AuthRequests.register(REGISTER_DTO);
        final String createdUserJwt = createdJwtDto.getJwt();
        final EditPasswordDto editPasswordDto = EditPasswordDto.builder()
                .username("Petr")
                .oldPassword("Petr")
                .newPassword("newPetr")
                .build();
        final String expectedResult = "Password of %s was edited".formatted(editPasswordDto.getUsername());

        String actualResult = UserRequests.editPassword(createdUserJwt, editPasswordDto);

        assertEquals(expectedResult, actualResult);
        UserRequests.deleteAccount(createdUserJwt, new CredentialsDto("Petr", "newPetr"));
    }

    @Test
    void editPassword_shouldThrowException_whenDataIsInvalid() {
        final String userJwt = getUserJwt();
        final EditPasswordDto editPasswordDto = EditPasswordDto.builder()
                .username("dummy")
                .oldPassword("dummy")
                .newPassword("newPassword")
                .build();

        assertThrows(RuntimeException.class, () -> UserRequests.editPassword(userJwt, editPasswordDto));
    }




    @Test
    void deleteAccount_shouldGetDeleteAccountString_whenDataIsValid() {
        final JwtDto createdJwtDto = AuthRequests.register(REGISTER_DTO);
        final String createdUserJwt = createdJwtDto.getJwt();
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Petr")
                .password("Petr")
                .build();
        final String expectedResult = "Account of %s was deleted".formatted(credentialsDto.getUsername());

        String actualResult = UserRequests.deleteAccount(createdUserJwt, credentialsDto);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteAccount_shouldThrowException_whenDataIsInvalid() {
        final String userJwt = getUserJwt();
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("dummy")
                .password("dummy")
                .build();

        assertThrows(RuntimeException.class, () -> UserRequests.deleteAccount(userJwt, credentialsDto));
    }




    @Test
    void createTask_shouldGetResponseTaskDto_whenDataIsValid() {
        final String userJwt = getUserJwt();

        ResponseTaskDto actualResult = UserRequests.createTask(userJwt, CREATE_TASK_DTO);

        assertNotNull(actualResult.getId());
        assertEquals(CREATE_TASK_DTO.getDescription(), actualResult.getDescription());
        assertEquals(CREATE_TASK_DTO.getDueDate(), actualResult.getDueDate());
        UserRequests.deleteTaskById(userJwt, actualResult.getId());
    }

    @Test
    void createTask_shouldThrowException_whenDataIsInvalid() {
        final String userJwt = getUserJwt();
        final CreateTaskDto createTaskDto = CreateTaskDto.builder()
                .description("")
                .dueDate(LocalDate.of(2000, 1, 1))
                .build();

        assertThrows(RuntimeException.class, () -> UserRequests.createTask(userJwt, createTaskDto));
    }




    @Test
    void getTodoList_shouldGetTaskListDto_whenDefaultParams() {
        final String userJwt = getUserJwt();
        final List<ResponseTaskDto> expectedResult = Stream.of(TASK, TASK_2, TASK_3)
                .map(Mapper::mapToResponseTaskDto)
                .toList();

        TaskListDto actualResult = UserRequests.getTodoList(userJwt);

        assertEquals(expectedResult, actualResult.getTaskList());
    }

    @Test
    void getTodoList_shouldGetPaginatedTaskListDto_whenCustomParams() {
        final String userJwt = getUserJwt();
        final List<ResponseTaskDto> expectedResult = Stream.of(TASK_3)
                .map(Mapper::mapToResponseTaskDto)
                .toList();

        TaskListDto actualResult = UserRequests.getTodoList(userJwt, 1, 2);

        assertEquals(expectedResult, actualResult.getTaskList());
    }

    @Test
    void getTodoList_shouldGetEmptyTaskListDto_whenNoTasksByCustomParams() {
        final String userJwt = getUserJwt();

        TaskListDto actualResult = UserRequests.getTodoList(userJwt, 999, 999);

        assertTrue(actualResult.getTaskList().isEmpty());
    }




    @Test
    void getTaskById_shouldGetResponseTaskDto_whenDataIsValid() {
        final String userJwt = getUserJwt();

        ResponseTaskDto actualResult = UserRequests.getTaskById(userJwt, TASK.getId());

        assertEquals(RESPONSE_TASK_DTO, actualResult);
    }

    @Test
    void getTaskById_shouldThrowException_whenNotExist() {
        final String userJwt = getUserJwt();

        assertThrows(RuntimeException.class, () -> UserRequests.getTaskById(userJwt, 999L));
    }




    @Test
    void updateTaskById_shouldGetUpdatedResponseTaskDto_whenDataIsValidAndExist() {
        final String userJwt = getUserJwt();
        final ResponseTaskDto createdTaskDto = UserRequests.createTask(userJwt, CREATE_TASK_DTO);

        ResponseTaskDto actualResult = UserRequests.updateTaskById(userJwt, UPDATE_TASK_DTO, createdTaskDto.getId());

        assertEquals(UPDATE_TASK_DTO.getDescription(), actualResult.getDescription());
        assertEquals(UPDATE_TASK_DTO.getDueDate(), actualResult.getDueDate());
        UserRequests.deleteTaskById(userJwt, createdTaskDto.getId());
    }

    @Test
    void updateTaskById_shouldThrowException_whenDataIsInvalid() {
        final String userJwt = getUserJwt();
        final UpdateTaskDto invalidUpdateTaskDto = UpdateTaskDto.builder()
                .description("")
                .dueDate(LocalDate.of(2000, 12, 30))
                .build();

        assertThrows(RuntimeException.class, () -> UserRequests.updateTaskById(userJwt, invalidUpdateTaskDto, TASK.getId()));
    }

    @Test
    void updateTaskById_shouldThrowException_whenNotExist() {
        final String userJwt = getUserJwt();

        assertThrows(RuntimeException.class, () -> UserRequests.updateTaskById(userJwt, UPDATE_TASK_DTO, 999L));
    }




    @Test
    void markAsCompletedById_shouldGetMarkAsCompletedString_whenExist() {
        final String userJwt = getUserJwt();
        final ResponseTaskDto createdTaskDto = UserRequests.createTask(userJwt, CREATE_TASK_DTO);
        final String expectedResult = "Task with id=" + createdTaskDto.getId() + " was marked as completed";

        String actualResult = UserRequests.markAsCompletedById(userJwt, createdTaskDto.getId());

        assertEquals(expectedResult, actualResult);
        UserRequests.deleteTaskById(userJwt, createdTaskDto.getId());
    }

    @Test
    void markAsCompletedById_shouldThrowException_whenNotExist() {
        final String userJwt = getUserJwt();

        assertThrows(RuntimeException.class, () -> UserRequests.markAsCompletedById(userJwt, 999L));
    }




    @Test
    void deleteTaskById_shouldGetDeletedTaskString_whenExist() {
        final String userJwt = getUserJwt();
        final ResponseTaskDto createdTaskDto = UserRequests.createTask(userJwt, CREATE_TASK_DTO);
        final String expectedResult = "Task with id=" + createdTaskDto.getId() + " was deleted" ;

        String actualResult = UserRequests.deleteTaskById(userJwt, createdTaskDto.getId());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteTaskById_shouldThrowException_whenNotExist() {
        final String userJwt = getUserJwt();

        assertThrows(RuntimeException.class, () -> UserRequests.deleteTaskById(userJwt, 999L));
    }



}


















