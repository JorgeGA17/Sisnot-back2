package sisnot.sisnot.Model.Dto;


import lombok.Data;
import sisnot.sisnot.Model.enums.ERole;

@Data

public class UserProfileDTO {

    private int id;
    private String email;
    private ERole role;

    private String firstName;
    private String lastName;

}
