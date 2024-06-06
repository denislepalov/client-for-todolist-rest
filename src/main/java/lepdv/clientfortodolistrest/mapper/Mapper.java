package lepdv.clientfortodolistrest.mapper;

import lepdv.clientfortodolistrest.dto.ResponseTaskDto;
import lepdv.clientfortodolistrest.dto.UserForAdminDto;
import lepdv.clientfortodolistrest.entity.Task;
import lepdv.clientfortodolistrest.entity.User;
import lombok.experimental.UtilityClass;


@UtilityClass
public class Mapper {


    public static UserForAdminDto mapToUserForAdminDto(User user) {
        return UserForAdminDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .dateOfBirth(user.getDateOfBirth())
                .role(user.getRole().name())
                .isNonLocked(user.getIsNonLocked())
                .build();
    }


    public static ResponseTaskDto mapToResponseTaskDto(Task task) {
       return ResponseTaskDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .dateOfCreation(task.getDateOfCreation())
                .dueDate(task.getDueDate())
                .isCompleted(task.getIsCompleted())
                .user(task.getUser().getUsername())
                .build();
    }


}
