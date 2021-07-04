package locations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateLocationCommand {

    private String name;
    private double lat;
    private double lon;

}
