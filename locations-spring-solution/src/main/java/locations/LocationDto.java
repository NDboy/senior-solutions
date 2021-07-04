package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private Long id;
    private String name;
    private double lat;
    private double lon;


}

//A LocationsController legyen @RestController,
// a getLocations() metóduson legyen @GetMapping annotáció!
// Hozd létre a LocationDto osztályt!
// Vezesd be a Lombok használatát!
// A DTO és az entitás közötti konvertálásra használj ModelMappert!
//
//        Ennek megfelelően változtasd a unit és integrációs teszteket,
//        ha arra szükség van!
