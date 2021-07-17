package locations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private Long id;
    private String name;
    private double lat;
    private double lon;

////    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
//
//    private LocalDateTime time;



}

//A LocationsController legyen @RestController,
// a getLocations() metóduson legyen @GetMapping annotáció!
// Hozd létre a LocationDto osztályt!
// Vezesd be a Lombok használatát!
// A DTO és az entitás közötti konvertálásra használj ModelMappert!
//
//        Ennek megfelelően változtasd a unit és integrációs teszteket,
//        ha arra szükség van!
