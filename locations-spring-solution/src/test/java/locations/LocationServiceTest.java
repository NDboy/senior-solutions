package locations;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


class LocationServiceTest {



    @Test
    void testGetLocations() {
        LocationService locationService = new LocationService();
        List<Location> locations = locationService.getLocations();

        assertThat(locations).hasSize(2).extracting("name", "lat").contains(tuple("Buenos Aires", -35.12144));

    }
}