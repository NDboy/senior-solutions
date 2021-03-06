package locations;

import java.util.Objects;

public class Location {

    private String name;

    private double lat;

    private double lon;

    private double alt;

    public Location(String name, double lat, double lon) {
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90!");
        }
        if (lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180!");
        }
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public Location(String name, double lat, double lon, double alt) {
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90!");
        }
        if (lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180!");
        }
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
    }

    public double distanceFrom(Location otherLocation) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(otherLocation.lat - lat);
        double lonDistance = Math.toRadians(otherLocation.lon - lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(otherLocation.lat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        double height = Location.this.alt - otherLocation.alt;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getAlt() {
        return alt;
    }

    @Override
    public String toString() {
        return name +
                ", lat=" + lat +
                ", lon=" + lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.lat, lat) == 0 && Double.compare(location.lon, lon) == 0 && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lat, lon);
    }
}

//    A locations csomagba dolgozz!
//
//        Hozz l??tre egy Location oszt??lyt, name, lat, lon attrib??tumokkal! A name attrib??tum String t??pus?? legyen!
//        A sz??less??gi ??s hossz??s??gi koordin??t??kat k??l??n double t??pus?? attrib??tummal ??br??zold!
//
//        Legyenek getter/setter met??dusai, ??s konstruktora, ahol mind a h??rom attrib??tum??t meg lehet adni!

//        Hozz l??tre egy LocationParser oszt??lyt, mely feladata sz??veges ??rt??kb??l kinyerni egy kedvenc hely adatait!
//        Legyen egy public Location parse(String text) met??dusa,
//        mely a nevet ??s a koordin??t??kat vessz??vel elv??lasztva v??rja (pl. Budapest,47.497912,19.040235)!
//        A tizedeshat??rol?? karakter legyen a pont! Ez a met??dus visszaad egy ??j p??ld??nyt, kit??ltve a megfelel?? attrib??tum ??rt??kekkel.
//        ??rj r?? egy LocationTest oszt??lyt, valamint egy testParse() met??dust, mely ezt a met??dust teszteli!