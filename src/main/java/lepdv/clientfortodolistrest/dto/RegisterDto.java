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
public class RegisterDto {

    private String username;
    private String password;
    private String fullName;
    private LocalDate dateOfBirth;


}
