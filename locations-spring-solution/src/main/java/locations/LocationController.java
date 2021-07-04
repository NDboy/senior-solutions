package locations;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    public List<LocationDto> getLocations(@RequestParam Optional<String> namePart, @RequestParam Optional<Double> minLat, @RequestParam Optional<Double> minLon) {
        return locationService.findLocationByNameOrMinLatOrMinLon(namePart, minLat, minLon);
    }

    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return locationService.findLocationById(id);
    }

    @PostMapping
    public LocationDto createLocation(@RequestBody CreateLocationCommand command) {
        return locationService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command) {
        return locationService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable("id") long id) {
        locationService.deleteLocation(id);
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

//    Lehessen megadni, hogy szűrni lehessen a kedvenc hely nevére!
//
//        Opcionális feladat: Megadhatsz további paramétereket is,
//        pl. minLat, minLon, maxLat és maxLon.
//        Ezekkel korlátokat tudsz megadni a kedvenc hely koordinátáira.
//
//        Lehessen lekérni kedvenc helyeket id alapján is!
//
//        Id generálására használj AtomicLong osztályt, szűrésre Java 8 streameket!

