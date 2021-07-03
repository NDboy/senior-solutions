package locations;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ExtendWith(SoftAssertionsExtension.class)
public class LocationServiceWithAssertJTest {

    private LocationService ls;

    private List<Location> favoriteLocations;


    @BeforeEach
    void Init() {
        ls = new LocationService();
        favoriteLocations = ls.loadLocations("favoritelocations.csv");
    }

    @Test
    void testListLocationsWithAssertJ() {
//        LocationService ls = new LocationService();
//        List<Location> favoriteLocations = ls.loadLocations("favoritelocations.csv");

        assertThat(favoriteLocations.get(0)).isInstanceOf(Location.class);

        assertThat(favoriteLocations)
                .hasSize(4)
                .extracting(Location::getName)
                .containsOnly("Budapest", "Pécs", "Sopron", "Kőszeg");

        assertThat(favoriteLocations)
                .extracting(Location::getAlt)
                .containsOnly(135.0,141.0,111.0,185.0);

        assertThat(favoriteLocations)
                .extracting(Location::getName, Location::getLat)
                .contains(tuple("Pécs", 46.325123));
    }

    @Test
    void testListLocationsWithSoftAssertions(SoftAssertions softly) {

        softly.assertThat(favoriteLocations.get(0)).isInstanceOf(Location.class);

        softly.assertThat(favoriteLocations)
                .hasSize(4)
                .extracting(Location::getName)
                .containsOnly("Budapest", "Pécs", "Sopron", "Kőszeg");

        softly.assertThat(favoriteLocations)
                .extracting(Location::getAlt)
                .containsOnly(135.0,141.0,111.0,185.0);

        softly.assertThat(favoriteLocations)
                .extracting(Location::getName, Location::getLat)
                .contains(tuple("Pécs", 46.325123));

    }
}

