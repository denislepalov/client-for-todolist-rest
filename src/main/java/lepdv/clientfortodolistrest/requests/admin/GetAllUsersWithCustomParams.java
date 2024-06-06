package lepdv.clientfortodolistrest.requests.admin;

import lepdv.clientfortodolistrest.dto.UserForAdminDto;
import lepdv.clientfortodolistrest.dto.UserListDto;
import lepdv.clientfortodolistrest.util.Jwt;

import java.util.List;

import static lepdv.clientfortodolistrest.util.AdminRequests.getAllUsers;


public class GetAllUsersWithCustomParams {

    public static void main(String[] args) {

        final String jwt = Jwt.getAdminJwt();

        UserListDto userList = getAllUsers(jwt, 1, 2);
        List<UserForAdminDto> allUsers = userList.getUserList();
        System.out.println(allUsers);

    }
}
