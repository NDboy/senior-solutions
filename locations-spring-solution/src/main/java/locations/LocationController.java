package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDto> getLocations() {
        return locationService.getLocations();
    }

}


//A LocationsController legyen @RestController,
// a getLocations() metóduson legyen @GetMapping annotáció!
// Hozd létre a LocationDto osztályt!
// Vezesd be a Lombok használatát!
// A DTO és az entitás közötti konvertálásra használj ModelMappert!
//
//        Ennek megfelelően változtasd a unit és integrációs teszteket,
//        ha arra szükség van!

