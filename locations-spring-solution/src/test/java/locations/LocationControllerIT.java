package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationControllerIT {

    @Autowired
    LocationController locationController;

    @Test
    void testGetLocations() {
        String result = locationController.getLocations();
        assertEquals("Location(id=1, name=Buenos Aires, lat=-35.12144, lon=-78.121244), Location(id=2, name=Brasil, lat=-39.12188, lon=-32.98756)", result);


    }


}
