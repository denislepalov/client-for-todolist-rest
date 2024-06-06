package lepdv.clientfortodolistrest.requests.user;

import lepdv.clientfortodolistrest.dto.EditPasswordDto;
import lepdv.clientfortodolistrest.util.Jwt;

import static lepdv.clientfortodolistrest.util.UserRequests.editPassword;

public class EditUserPassword {

    public static void main(String[] args) {

        final String jwt = Jwt.getUserJwt();

        EditPasswordDto editPassword = EditPasswordDto.builder()
                .username("Ivan")
                .oldPassword("Ivan")
                .newPassword("newIvan")
                .build();
        String response = editPassword(jwt, editPassword);
        System.out.println(response);

    }
}
