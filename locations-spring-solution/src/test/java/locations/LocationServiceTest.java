package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


class LocationServiceTest {

    LocationService locationService;

    @BeforeEach
    void init() {
        ModelMapper mm = new ModelMapper();
        locationService = new LocationService(mm);
        List<LocationDto> locationDtos = locationService.getLocations();
    }

    @Test
    void testGetLocations() {
        List<LocationDto> locationDtos = locationService.getLocations();

        assertThat(locationDtos)
                .hasSize(2)
                .extracting("name", "lat")
                .contains(tuple("Buenos Aires", -35.12144));
    }

    @Test
    void testFindLocationById() {
        LocationDto foundLocationDto = locationService.findLocationById(1);
        assertThat(foundLocationDto)
                .extracting("id", "name")
                .contains(1L, "Buenos Aires");

        LocationDto foundLocationDto2 = locationService.findLocationById(2);
        assertThat(foundLocationDto2)
                .extracting("id", "name")
                .contains(2L, "Brasil");
    }




}