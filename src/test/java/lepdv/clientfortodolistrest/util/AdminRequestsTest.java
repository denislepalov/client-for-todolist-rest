package lepdv.clientfortodolistrest.util;

import lepdv.clientfortodolistrest.dto.*;
import lepdv.clientfortodolistrest.mapper.Mapper;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Stream;

import static lepdv.clientfortodolistrest.Constants.*;
import static lepdv.clientfortodolistrest.util.AdminRequests.*;
import static lepdv.clientfortodolistrest.util.Jwt.getAdminJwt;
import static org.junit.jupiter.api.Assertions.*;


class AdminRequestsTest {



    @Test
    void test_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<AdminRequests> constructor = AdminRequests.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }




    @Test
    void getAllUsers_shouldGetUserListDto_whenDefaultParams() {
        final String adminJwt = getAdminJwt();
        final List<UserForAdminDto> expectedResult = USER_LIST.stream()
                .map(Mapper::mapToUserForAdminDto)
                .toList();

        UserListDto actualResult = AdminRequests.getAllUsers(adminJwt);

        assertEquals(expectedResult, actualResult.getUserList());
    }

    @Test
    void getAllUsers_shouldGetPaginatedUserListDto_whenCustomParams() {
        final String adminJwt = getAdminJwt();
        final List<UserForAdminDto> expectedResult = Stream.of(KATYA)
                .map(Mapper::mapToUserForAdminDto)
                .toList();

        UserListDto actualResult = AdminRequests.getAllUsers(adminJwt, 1, 2);

        assertEquals(expectedResult, actualResult.getUserList());
    }

    @Test
    void getAllUsers_shouldGetEmptyUserListDto_whenNoUsersByCustomParams() {
        final String adminJwt = getAdminJwt();

        UserListDto actualResult = AdminRequests.getAllUsers(adminJwt, 999, 999);

        assertTrue(actualResult.getUserList().isEmpty());
    }




    @Test
    void getUserById_shouldGetUserForAdminDto_whenExist() {
        final String adminJwt = getAdminJwt();

        UserForAdminDto actualResult = AdminRequests.getUserById(adminJwt, 2L);

        assertEquals(USER_FOR_ADMIN_DTO, actualResult);
    }

    @Test
    void getUserById_shouldThrowException_whenNotExist() {
        final String adminJwt = getAdminJwt();

        assertThrows(RuntimeException.class, () -> AdminRequests.getUserById(adminJwt, 999L));
    }




    @Test
    void lockUser_shouldLockUser_whenExist() {
        final String adminJwt = getAdminJwt();
        final String expectedResult = "User id=" + USER.getId() + " was locked";

        String actualResult = lockUserById(adminJwt, 2L);

        assertEquals(expectedResult, actualResult);
        AdminRequests.unlockUserById(adminJwt, USER.getId());
    }

    @Test
    void lockUser_shouldThrowException_whenNotExist() {
        final String adminJwt = getAdminJwt();

        assertThrows(RuntimeException.class, () -> lockUserById(adminJwt, 999L));
    }




    @Test
    void unlockUser_shouldUnlockUser_whenExist() {
        final String adminJwt = getAdminJwt();
        final String expectedResult = "User id=" + USER.getId() + " was unlocked";
        AdminRequests.lockUserById(adminJwt, USER.getId());

        String actualResult = unlockUserById(adminJwt, USER.getId());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void unlockUser_shouldThrowException_whenNotExist() {
        final String adminJwt = getAdminJwt();

        assertThrows(RuntimeException.class, () -> unlockUserById(adminJwt, 999L));
    }




    @Test
    void deleteUser_shouldDeleteUser_whenExist() {
        AuthRequests.register(REGISTER_DTO);
        final String adminJwt = getAdminJwt();
        final List<UserForAdminDto> userList = getAllUsers(adminJwt).getUserList();
        final UserForAdminDto createdUser = userList.get(userList.size() - 1);
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Admin")
                .password("Admin")
                .build();
        final String expectedResult = "User id=" + createdUser.getId() + " was deleted";

        String actualResult = deleteUserById(adminJwt, createdUser.getId(), credentialsDto);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteUser_shouldThrowException_whenNotExist() {
        final String adminJwt = getAdminJwt();
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Admin")
                .password("Admin")
                .build();

        assertThrows(RuntimeException.class, () -> deleteUserById(adminJwt, 999L, credentialsDto));
    }




    @Test
    void getAllTasks_shouldGetTaskListDto_whenDefaultParams() {
        final String adminJwt = getAdminJwt();
        List<ResponseTaskDto> expectedResult = TASK_LIST.stream()
                .map(Mapper::mapToResponseTaskDto)
                .toList();

        TaskListDto actualResult = getAllTasks(adminJwt);

        assertEquals(expectedResult, actualResult.getTaskList());
    }

    @Test
    void getAllTasks_shouldGetPaginatedTaskListDto_whenCustomParams() {
        final String adminJwt = getAdminJwt();
        List<ResponseTaskDto> expectedResult = Stream.of(TASK_3, TASK_4)
                .map(Mapper::mapToResponseTaskDto)
                .toList();

        TaskListDto actualResult = getAllTasks(adminJwt, 1, 2);

        assertEquals(expectedResult, actualResult.getTaskList());
    }

    @Test
    void getAllTasks_shouldGetEmptyTaskListDto_whenNoTasksByCustomParams() {
        final String adminJwt = getAdminJwt();

        TaskListDto actualResult = getAllTasks(adminJwt, 999, 999);

        assertTrue(actualResult.getTaskList().isEmpty());
    }




    @Test
    void getTaskById_shouldGetResponseTaskDto_whenExist() {
        final String adminJwt = getAdminJwt();

        ResponseTaskDto actualResult = getTaskById(adminJwt, 1L);

        assertEquals(RESPONSE_TASK_DTO, actualResult);
    }

    @Test
    void getTaskById_shouldThrowException_whenNotExist() {
        final String adminJwt = getAdminJwt();

        assertThrows(RuntimeException.class, () -> getTaskById(adminJwt, 999L));
    }



}











