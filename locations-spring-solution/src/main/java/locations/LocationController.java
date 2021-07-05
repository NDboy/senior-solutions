package locations;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
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
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocation(@Valid @RequestBody CreateLocationCommand command) {
        return locationService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id, @Valid @RequestBody UpdateLocationCommand command) {
        return locationService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id) {
        locationService.deleteLocation(id);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFoundException(LocationNotFoundException lnfe){
        Problem problem = Problem.builder()
                .withType(URI.create("locations/not-found-by-me"))
                .withTitle("Not found by me!")
                .withStatus(Status.NOT_FOUND)
                .withDetail(lnfe.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);

    }

    public void deleteAllLocations() {
        locationService.deleteAllLocations();
    }


}


//    Módosítsd az alkalmazásod, hogy létrehozáskor 201-es státuszkóddal,
//    törléskor 204-es státuszkóddal térjen vissza!
//
//        Módosítsd az alkalmazásod,
//        hogy az RFC szabványnak megfelelő JSON legyen az üzenet törzsében!
//
//        Módosítsd, hogy nem található kedvenc hely esetén
//        404-es státuszkód jöjjön vissza! Használj saját kivételt, pl. LocationNotFoundException!


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

