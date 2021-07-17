package navsolution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Intervall {

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
