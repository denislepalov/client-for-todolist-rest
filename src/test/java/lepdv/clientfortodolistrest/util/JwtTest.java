package lepdv.clientfortodolistrest.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.util.StringUtils.hasText;


class JwtTest {



    @Test
    void test_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<Jwt> constructor = Jwt.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }



    @Test
    void getAdminJwt_shouldGetAdminJwt() {
        String actualResult = Jwt.getAdminJwt();

        assertTrue(hasText(actualResult));
    }



    @Test
    void getUserJwt_shouldGetUserJwt() {
        String actualResult = Jwt.getUserJwt();

        assertTrue(hasText(actualResult));
    }



    @Test
    void getKatyaJwt_shouldGerKatyaJwt() {
        String actualResult = Jwt.getKatyaJwt();

        assertTrue(hasText(actualResult));
    }


}