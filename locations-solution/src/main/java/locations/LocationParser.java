package locations;

public class LocationParser {

    public Location parse(String locationString) {
        String name = locationString.split(",")[0];
        double lat = Double.parseDouble(locationString.split(",")[1]);
        double lon = Double.parseDouble(locationString.split(",")[2]);
        return new Location(name, lat, lon);
    }

    public boolean isOnEquator(Location location) {
        return location.getLat() == 0.0;
    }

    public boolean isOnPrimeMeridian(Location location) {
        return location.getLon() == 0.0;
    }


}

//    A locations csomagba dolgozz!
//
//        Hozz létre egy Location osztályt, name, lat, lon attribútumokkal! A name attribútum String típusú legyen!
//        A szélességi és hosszúsági koordinátákat külön double típusú attribútummal ábrázold!
//
//        Legyenek getter/setter metódusai, és konstruktora, ahol mind a három attribútumát meg lehet adni!

//        Hozz létre egy LocationParser osztályt, mely feladata szöveges értékből kinyerni egy kedvenc hely adatait!
//        Legyen egy public Location parse(String text) metódusa,
//        mely a nevet és a koordinátákat vesszővel elválasztva várja (pl. Budapest,47.497912,19.040235)!
//        A tizedeshatároló karakter legyen a pont! Ez a metódus visszaad egy új példányt, kitöltve a megfelelő attribútum értékekkel.
//        Írj rá egy LocationTest osztályt, valamint egy testParse() metódust, mely ezt a metódust teszteli!
