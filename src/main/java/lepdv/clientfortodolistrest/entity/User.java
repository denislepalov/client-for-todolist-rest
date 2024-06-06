package lepdv.clientfortodolistrest.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "todoList")
@ToString(exclude = "todoList")
@Builder
public class User {

    private Long id;
    private String username;
    private String password;
    private String fullName;
    private LocalDate dateOfBirth;
    private Role role;
    private Boolean isNonLocked;
    private List<Task> todoList;


//    @Override
//    public User clone() {
//        try {
//            return (User) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }
}

