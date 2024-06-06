package lepdv.clientfortodolistrest.mapper;

import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.dto.UserForAdminDto;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static lepdv.clientfortodolistrest.Constants.*;
import static org.junit.jupiter.api.Assertions.*;


class MapperTest {



    @Test
    void test_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<Mapper> constructor = Mapper.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }



    @Test
    void mapToUserForAdminDto_shouldMapUserToUserForAdminDto() {
        UserForAdminDto actualResult = Mapper.mapToUserForAdminDto(USER);

        assertEquals(USER_FOR_ADMIN_DTO, actualResult);
    }



    @Test
    void mapToResponseTaskDto_shouldMapTaskToResponseTaskDto() {
        ResponseTaskDto actualResult = Mapper.mapToResponseTaskDto(TASK);

        assertEquals(RESPONSE_TASK_DTO, actualResult);
    }



}



