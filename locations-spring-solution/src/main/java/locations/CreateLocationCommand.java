package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {

    @NotBlank(message = "Name can not be blank")
    private String name;

    @Min(-90)
    @Max(90)
    private double lat;

    @Min(-180)
    @Max(180)
    private double lon;

}
