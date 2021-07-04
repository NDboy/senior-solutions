package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Arrays;


import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    LocationService locationService;

    @InjectMocks
    LocationController locationController;


    @Test
    void testGetLocations() {
        when(locationService.getLocations()).thenReturn(Arrays.asList(
                new LocationDto(1L, "TestCase1", -35.12144, -78.121244),
                new LocationDto(2L, "TestCase2", -39.12188, -32.98756)
        ));

        assertThat(locationController.getLocations())
                .hasSize(2)
                .extracting("name", "lat")
                .contains(tuple("TestCase1", -35.12144));

        verify(locationService).getLocations();

    }
}