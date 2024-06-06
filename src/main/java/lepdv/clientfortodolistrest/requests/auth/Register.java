package lepdv.clientfortodolistrest.requests.auth;
import lepdv.clientfortodolistrest.dto.JwtDto;
import lepdv.clientfortodolistrest.dto.RegisterDto;

import java.time.LocalDate;
import java.time.Month;

import static lepdv.clientfortodolistrest.util.AuthRequests.register;


public class Register {

    public static void main(String[] args) {

        RegisterDto newUser = RegisterDto.builder()
                .username("Petr")
                .password("Petr")
                .fullName("Petrov Petr")
                .dateOfBirth(LocalDate.of(1980, Month.AUGUST, 15))
                .build();
        JwtDto response = register(newUser);
        System.out.println(response);
        String jwt = response.getJwt();
        System.out.println(jwt);

    }


}
