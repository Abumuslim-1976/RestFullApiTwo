package uz.pdp.RestFullApiTwo.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {

    @NotNull(message = "ERROR !!! , enter new task name")
    private String name;

    @NotNull(message = "ERROR !!! , enter new task text")
    private String text;

    private String help;

    @NotNull(message = "ERROR !!! , enter language ID")
    private Integer languageId;
}
