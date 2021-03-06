package locations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
//@Scope(value="prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)

public class LocationService {

    private AtomicLong atomicLong = new AtomicLong();

    private ModelMapper modelMapper;

    private List<Location> locations = new ArrayList<>();


    public LocationService(ModelMapper modelMapper) {
//        locations.add(new Location(atomicLong.incrementAndGet(), "Buenos Aires", -35.12144, -78.121244));
//        locations.add(new Location(atomicLong.incrementAndGet(), "Brasil", -39.12188, -32.98756));
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
                .orElseThrow(() -> new LocationNotFoundException("Location not found!"));
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

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(atomicLong.incrementAndGet(), command.getName(), command.getLat(), command.getLon()/*, command.getTime()*/);
        locations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Location not found!"));
        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        return modelMapper.map(location, LocationDto.class);

    }

    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Location not found!"));
        locations.remove(location);
    }

    public void deleteAllLocations() {
        atomicLong = new AtomicLong();
        locations.clear();
    }
}
//    Lehessen megadni, hogy sz??rni lehessen a kedvenc hely nev??re!
//
//        Opcion??lis feladat: Megadhatsz tov??bbi param??tereket is,
//        pl. minLat, minLon, maxLat ??s maxLon.
//        Ezekkel korl??tokat tudsz megadni a kedvenc hely koordin??t??ira.
//
//        Lehessen lek??rni kedvenc helyeket id alapj??n is!
//
//        Id gener??l??s??ra haszn??lj AtomicLong oszt??lyt, sz??r??sre Java 8 streameket!
