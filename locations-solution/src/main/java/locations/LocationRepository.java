package locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LocationRepository {

    List<Location> locations = new ArrayList<>();

    default void addLocation(Location location) {
        locations.add(location);
    }

    default List<Location> getLocations() {
        return locations;
    }

    default Optional<Location> findByName(String name) {

        return locations.stream()
                .filter(l -> l.getName().equals(name))
                .findFirst();
    }

}


//    Hozz létre egy LocationRepository interfészt,
//    melynek van egy Optional<Location> findByName(String name) metódusa!

//        Hozz létre egy DistanceService osztályt,
//        abban egy Optional<Double> calculateDistance(String name1, String name2) metódust!
//        A metódus a két névvel hívja meg a repository findByName() metódusát!
//        Amennyiben az egyik is Optional.empty() értékkel tér vissza,
//        adjon vissza egy Optional.empty() értéket!
//        Amennyiben egyik sem empty(), adja vissza a kettő közötti távolságot!
//        Egyrészt ellenőrizd, hogy jó névvel lett-e meghívva a calculateDistance() metódus!
//        Másrészt ellenőrizd nem létező nevekkel és létező nevekkel is a helyes működést.
//        (Mockolni kell a repository-t!)