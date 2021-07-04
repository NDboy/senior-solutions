package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


class LocationServiceTest {


    @Test
    void testGetLocations() {
        ModelMapper mm = new ModelMapper();
        LocationService locationService = new LocationService(mm);

        List<LocationDto> locationDtos = locationService.getLocations();

        assertThat(locationDtos)
                .hasSize(2)
                .extracting("name", "lat")
                .contains(tuple("Buenos Aires", -35.12144));
    }
}