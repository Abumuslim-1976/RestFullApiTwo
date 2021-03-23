package uz.pdp.RestFullApiTwo.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "ERROR !!! , enter email")
    private String email;

    @NotNull(message = "ERROR !!! , enter password")
    private String password;

}
