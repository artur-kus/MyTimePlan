package top.arturkus.MyTimePlan.helpers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StarHelper {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Positive(message = "Distance must be positive")
    private long distance;
}
