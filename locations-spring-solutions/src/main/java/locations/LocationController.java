package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
public class LocationController {

    private AtomicLong atomicLong = new AtomicLong();

    private List<Location> locations = Arrays.asList(
            new Location(atomicLong.incrementAndGet(), "Buenos Aires", -35.12144, -78.121244),
            new Location(atomicLong.incrementAndGet(), "Brasil", -39.12188, -32.98756)
    );

    @GetMapping("/locations")
    public String getLocations() {
        return locations
                .stream()
                .map(Location::toString)
                .collect(Collectors.joining(", "));
    }

}
