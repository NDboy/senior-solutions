package locations;

import java.util.Optional;

public class DistanceService {

    private LocRepository repository;

    public DistanceService(LocRepository repository) {
        this.repository = repository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location> loc1 = repository.findByName(name1);
        Optional<Location> loc2 = repository.findByName(name2);
        if (loc1.isPresent() && loc2.isPresent()) {
            return Optional.of(loc1.get().distanceFrom(loc2.get()));
        }
        return Optional.empty();
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
