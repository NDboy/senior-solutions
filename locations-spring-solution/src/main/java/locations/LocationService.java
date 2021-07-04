package locations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    public LocationDto findLocationById(long id) {
        Location foundLocation = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Location not found!"));
        return modelMapper.map(foundLocation, LocationDto.class);
    }

    public List<LocationDto> findLocationByNameOrMinLatOrMinLon(Optional<String> namePart, Optional<Double> minLat, Optional<Double> minLon) {
        return locations.stream()
                .filter(l -> namePart.isEmpty() || l.getName().toLowerCase().contains(namePart.get().toLowerCase()))
                .filter(l -> minLat.isEmpty() || l.getLat() >= minLat.get())
                .filter(l -> minLon.isEmpty() || l.getLon() >= minLon.get())
                .map(l -> modelMapper.map(l, LocationDto.class))
                .toList();
    }

}
//    Lehessen megadni, hogy szűrni lehessen a kedvenc hely nevére!
//
//        Opcionális feladat: Megadhatsz további paramétereket is,
//        pl. minLat, minLon, maxLat és maxLon.
//        Ezekkel korlátokat tudsz megadni a kedvenc hely koordinátáira.
//
//        Lehessen lekérni kedvenc helyeket id alapján is!
//
//        Id generálására használj AtomicLong osztályt, szűrésre Java 8 streameket!
