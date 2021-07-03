package locations;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class ControllerConfig {
}



//        Hozz létre egy locations-spring projektet,
//        benne egy LocationsController osztályt,
//        mely visszaadja a kedvenc helyeket, egyelőre Stringként!
//
//        Egy kedvenc helyet a Location osztály reprezentál.
//        Rendelkezik egy azonosítóval,
//        névvel és két koordinátával (rendre Long id, String name,
//        double lat, double lon).
//
//        Ezek egy listája legyen a controllerben.
//        A getLocations() metódus ezeket adja vissza.
//        Implementáld a Location osztály toString() metódusát!