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
public class UserForAdminDto {

    private Long id;
    private String username;
    private String fullName;
    private LocalDate dateOfBirth;
    private String role;
    private Boolean isNonLocked;

}
