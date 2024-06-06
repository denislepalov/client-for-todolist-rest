package lepdv.clientfortodolistrest.util;

import lepdv.clientfortodolistrest.dto.CredentialsDto;
import lepdv.clientfortodolistrest.dto.JwtDto;
import lepdv.clientfortodolistrest.dto.RegisterDto;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.Month;

import static lepdv.clientfortodolistrest.Constants.REGISTER_DTO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.util.StringUtils.hasText;


class AuthRequestsTest {



    @Test
    void test_cannot_instantiate() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<AuthRequests> constructor = AuthRequests.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }




    @Test
    void performLogin_shouldGetJwtDto_whenDataIsValid() {
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Ivan")
                .password("Ivan")
                .build();

        JwtDto actualResult = AuthRequests.performLogin(credentialsDto);

        assertTrue(hasText(actualResult.getJwt()));
    }

    @Test
    void performLogin_shouldThrowException_whenDataIsInvalid() {
        final CredentialsDto credentialsDto = CredentialsDto.builder()
                .username("Ivan")
                .password("dummy")
                .build();

        assertThrows(RuntimeException.class, () -> AuthRequests.performLogin(credentialsDto));
    }




    @Test
    void register_shouldGetJwtDto_whenDataIsValid() {
        JwtDto actualResult = AuthRequests.register(REGISTER_DTO);

        assertTrue(hasText(actualResult.getJwt()));
        UserRequests.deleteAccount(actualResult.getJwt(),
                new CredentialsDto(REGISTER_DTO.getUsername(), REGISTER_DTO.getPassword()));
    }

    @Test
    void register_shouldThrowException_whenDataIsInvalid() {
        final RegisterDto registerDto = RegisterDto.builder()
                .username("P")
                .password("")
                .fullName("Petrov Petr")
                .dateOfBirth(LocalDate.of(2100, Month.AUGUST, 15))
                .build();

        assertThrows(RuntimeException.class, () -> AuthRequests.register(registerDto));
    }




    @Test
    void getJwt() {

    }


}










