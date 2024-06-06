package lepdv.clientfortodolistrest.entity;


import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "user", callSuper = false)
@ToString(exclude = "user")
@Builder
public class Task {

    private Long id;
    private String description;
    private LocalDate dateOfCreation;
    private LocalDate dueDate;
    private String isCompleted;
    private User user;
    protected String createdBy;
    private String modifiedBy;



//    @Override
//    public Task clone() {
//        try {
//            return (Task) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }



}
