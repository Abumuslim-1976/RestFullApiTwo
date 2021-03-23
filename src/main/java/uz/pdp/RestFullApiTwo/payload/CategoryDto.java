package uz.pdp.RestFullApiTwo.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CategoryDto {

    @NotNull(message = "ERROR !!! , enter new Category")
    private String name;

    private String description;

    @NotNull(message = "ERROR !!! , enter languages")
    private List<Integer> languageId;
}
