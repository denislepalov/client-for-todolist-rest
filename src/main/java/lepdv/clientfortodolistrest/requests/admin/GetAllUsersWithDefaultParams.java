package lepdv.clientfortodolistrest.requests.admin;

import lepdv.clientfortodolistrest.dto.UserForAdminDto;
import lepdv.clientfortodolistrest.dto.UserListDto;
import lepdv.clientfortodolistrest.util.AdminRequests;
import lepdv.clientfortodolistrest.util.Jwt;

import java.util.List;


public class GetAllUsersWithDefaultParams {

    public static void main(String[] args) {

        final String jwt = Jwt.getAdminJwt();

        UserListDto userList = AdminRequests.getAllUsers(jwt);
        List<UserForAdminDto> allUsers = userList.getUserList();
        System.out.println(allUsers);

    }
}
