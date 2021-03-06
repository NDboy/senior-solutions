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


//    M??dos??tsd az alkalmaz??sod, hogy l??trehoz??skor 201-es st??tuszk??ddal,
//    t??rl??skor 204-es st??tuszk??ddal t??rjen vissza!
//
//        M??dos??tsd az alkalmaz??sod,
//        hogy az RFC szabv??nynak megfelel?? JSON legyen az ??zenet t??rzs??ben!
//
//        M??dos??tsd, hogy nem tal??lhat?? kedvenc hely eset??n
//        404-es st??tuszk??d j??jj??n vissza! Haszn??lj saj??t kiv??telt, pl. LocationNotFoundException!


//A LocationsController legyen @RestController,
// a getLocations() met??duson legyen @GetMapping annot??ci??!
// Hozd l??tre a LocationDto oszt??lyt!
// Vezesd be a Lombok haszn??lat??t!
// A DTO ??s az entit??s k??z??tti konvert??l??sra haszn??lj ModelMappert!
//
//        Ennek megfelel??en v??ltoztasd a unit ??s integr??ci??s teszteket,
//        ha arra sz??ks??g van!

//    Lehessen megadni, hogy sz??rni lehessen a kedvenc hely nev??re!
//
//        Opcion??lis feladat: Megadhatsz tov??bbi param??tereket is,
//        pl. minLat, minLon, maxLat ??s maxLon.
//        Ezekkel korl??tokat tudsz megadni a kedvenc hely koordin??t??ira.
//
//        Lehessen lek??rni kedvenc helyeket id alapj??n is!
//
//        Id gener??l??s??ra haszn??lj AtomicLong oszt??lyt, sz??r??sre Java 8 streameket!

