package uz.pdp.RestFullApiTwo.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LanguageDto {

    @NotNull(message = "ERROR !!! , enter new Language")
    private String name;
}
