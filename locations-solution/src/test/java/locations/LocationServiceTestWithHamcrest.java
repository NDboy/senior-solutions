package locations;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocationServiceTestWithHamcrest {

    @Test
    void testListLocationsWithHamcrest() {
        LocationService ls = new LocationService();
        List<Location> favoriteLocations = ls.loadLocations("favoritelocations.csv");

        assertThat(favoriteLocations, contains(
                hasProperty("name", equalTo("Budapest")),
                hasProperty("lat", equalTo(46.325123)),
                hasProperty("lon", equalTo(23.32111)),
                hasProperty("name", equalTo("Kőszeg"))
        ));
    }

}
