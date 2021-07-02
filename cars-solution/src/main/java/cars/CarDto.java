package cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private String brand;
    private String type;
    private int year;
    private Condition condition;
    private List<KmState> states;



}
