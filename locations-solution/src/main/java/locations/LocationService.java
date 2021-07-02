package locations;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    public List<Location> loadLocations(String pathString) {
        List<Location> locations = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(pathString))){
            br.lines()
                    .map(this::processLine)
                    .forEach(locations::add);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file!", ioe);
        }
        return locations;
    }

    private Location processLine(String lineString) {
        String name = lineString.split(";")[0];
        double lat = Double.parseDouble(lineString.split(";")[1]);
        double lon = Double.parseDouble(lineString.split(";")[2]);
        double alt = Double.parseDouble(lineString.split(";")[3]);
        return new Location(name, lat, lon, alt);
    }
}
