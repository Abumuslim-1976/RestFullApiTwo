package uz.pdp.RestFullApiTwo.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {

    @NotNull(message = "ERROR !!! , enter new text")
    private String text;

    @NotNull(message = "ERROR !!! , enter task ID")
    private Integer taskId;

    @NotNull(message = "ERROR !!! , enter user ID")
    private Integer userId;

}
