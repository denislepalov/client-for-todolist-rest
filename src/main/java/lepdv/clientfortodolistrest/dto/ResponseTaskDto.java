package lepdv.clientfortodolistrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseTaskDto {

    private Long id;
    private String description;
    private LocalDate dateOfCreation;
    private LocalDate dueDate;
    private String isCompleted;
    private String user;




}
