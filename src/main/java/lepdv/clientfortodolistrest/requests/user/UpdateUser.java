package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.dto.UserDto;
import lepdv.clientfortodolistrest.util.Jwt;

import java.time.LocalDate;
import java.time.Month;

import static lepdv.clientfortodolistrest.util.UserRequests.updateUser;

public class UpdateUser {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        UserDto userDto = UserDto.builder()
                .username("Ivan")
                .fullName("Updated full name")
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .build();
        UserDto response = updateUser(jwt, userDto);
        System.out.println(response);

    }
}
