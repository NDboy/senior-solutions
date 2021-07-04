package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationControllerIT {

    @Autowired
    LocationController locationController;

    @Test
    void testGetLocations() {
        List<LocationDto> locationDtos = locationController.getLocations();

        assertThat(locationDtos)
                .hasSize(2)
                .extracting("name", "lat")
                .contains(tuple("Buenos Aires", -35.12144));
    }

}
