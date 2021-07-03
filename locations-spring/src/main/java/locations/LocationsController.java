package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Controller
public class LocationsController {

    private AtomicLong atomicLong = new AtomicLong();

    private List<Location> locations = Arrays.asList(
            new Location(atomicLong.incrementAndGet(), "Buenos Aires", -35.12144, -78.121244),
            new Location(atomicLong.incrementAndGet(), "Brasil", -39.12188, -32.98756)
    );

    public List<Location> getLocations() {
        return locations;
    }

    @GetMapping("/")
    @ResponseBody
    public String listLocations() {
         return getLocations()
                .stream()
                .map(Location::getName)
                .collect(Collectors.joining(", "));
    }


}


//        Hozz létre egy locations-spring projektet,
//        benne egy LocationsController osztályt,
//        mely visszaadja a kedvenc helyeket, egyelőre Stringként!
//
//        Egy kedvenc helyet a Location osztály reprezentál.
//        Rendelkezik egy azonosítóval,
//        névvel és két koordinátával (rendre Long id, String name, double lat, double lon).
//
//        Ezek egy listája legyen a controllerben.
//        A getLocations() metódus ezeket adja vissza.
//        Implementáld a Location osztály toString() metódusát!
