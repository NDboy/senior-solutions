package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    private List<Location> locations;

    private LocationOperators locationOperators;

    @BeforeEach
    void Init() {
        Location budapestLocation = new Location("Budapest", 47.497912, 19.040235, 105);
        Location debrecenLocation = new Location("Debrecen", 47.53, 21.639167, 121);
        Location szegedLocation = new Location("Szeged", 46.25, 20.166667, 75);
        Location sydneyLocation = new Location("Sydney", -33.865, 151.209444);
        Location melbourneLocation = new Location("Melbourne", -37.820556, 144.961389);
        locations = new ArrayList<>();
        locations.add(budapestLocation);
        locations.add(debrecenLocation);
        locations.add(szegedLocation);
        locations.add(sydneyLocation);
        locations.add(melbourneLocation);
        locationOperators = new LocationOperators();
    }

    @Test
    void testFilterOnNorth() {
        List<Location> locationsOnNorth = locationOperators.filterOnNorth(locations);
        assertEquals(List.of("Budapest", "Debrecen", "Szeged"), locationsOnNorth.stream().map(Location::getName).collect(Collectors.toList()));
    }

}