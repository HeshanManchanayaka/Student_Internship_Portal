package edu.icet.dtos;

import edu.icet.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private String companyName;
    private String course;
    private String institution;
    private String phone;
}
