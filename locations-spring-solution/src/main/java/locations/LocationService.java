package locations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LocationService {

    private AtomicLong atomicLong = new AtomicLong();

    private ModelMapper modelMapper;

    private List<Location> locations = Arrays.asList(
            new Location(atomicLong.incrementAndGet(), "Buenos Aires", -35.12144, -78.121244),
            new Location(atomicLong.incrementAndGet(), "Brasil", -39.12188, -32.98756)
    );

    public LocationService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations() {
        return locations.stream()
                .map(l -> modelMapper.map(l, LocationDto.class))
                .toList();
    }


}
