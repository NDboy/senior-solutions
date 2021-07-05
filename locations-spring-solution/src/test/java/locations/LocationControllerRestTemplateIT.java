package locations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerRestTemplateIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testListLocations() {
        LocationDto locationDto = testRestTemplate.postForObject("/api/locations", new CreateLocationCommand("Puerto Rico", -18.00, -66.1234), LocationDto.class);
        assertEquals("Puerto Rico", locationDto.getName());

        testRestTemplate.postForObject("/api/locations", new CreateLocationCommand("Lima", -12.02, -77.02), LocationDto.class);

        List<LocationDto> locationDtos = testRestTemplate.exchange("/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {})
                .getBody();
        assertThat(locationDtos)
                .hasSize(4)
                .extracting(LocationDto::getName, LocationDto::getLat)
                .contains(tuple("Puerto Rico", -18.00), tuple("Lima", -12.02));
    }

    @Test
    void testFindLocationById() {
        LocationDto locationDto = testRestTemplate.getForObject("/api/locations/{id}", LocationDto.class, 1);
        assertEquals("Buenos Aires", locationDto.getName());
        LocationDto locationDto2 = testRestTemplate.getForObject("/api/locations/{id}", LocationDto.class, 2);
        assertEquals("Brasil", locationDto2.getName());
    }

    @Test
    void testUpdateLocation() {
        testRestTemplate.put("/api/locations/{id}", new UpdateLocationCommand("Buenos Aires", -34.12144, -58.44), 1);
        LocationDto locationDto = testRestTemplate.getForObject("/api/locations/{id}", LocationDto.class, 1);
        assertEquals(-58.44, locationDto.getLon());
    }

    @Test
    void testDeleteLocation() {
        testRestTemplate.delete("/api/locations/{id}", 1);
        List<LocationDto> locationDtos = testRestTemplate.exchange("/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {})
                .getBody();
        assertThat(locationDtos)
                .hasSize(1)
                .extracting(LocationDto::getName, LocationDto::getLat)
                .containsOnly(tuple("Brasil", -39.12188));
    }

    @Test
    void createLocationWithInvalidName() {
        Problem result = testRestTemplate.postForObject("/api/locations",
                new CreateLocationCommand("", 47.023, 74.221),
                Problem.class);

        assertEquals(Status.BAD_REQUEST, result.getStatus());

    }

    @Test
    void createLocationWithInvalidLat() {
        Problem result = testRestTemplate.postForObject("/api/locations",
                new CreateLocationCommand("NotOnEarth", -147.023, 74.221),
                Problem.class);
        assertEquals(Status.BAD_REQUEST, result.getStatus());

    }

    @Test
    void createLocationWithInvalidLon() {
        Problem result = testRestTemplate.postForObject("/api/locations",
                new CreateLocationCommand("NotOnEarth", -47.023, 274.221),
                Problem.class);
        assertEquals(Status.BAD_REQUEST, result.getStatus());

    }
}
