package locations;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    LocationParser locationParser;


    @BeforeEach
    void init() {
        locationParser = new LocationParser();
    }

    @Test
    @DisplayName("The test of location-parsing")
    void testParse() {
        Location location = locationParser.parse("Budapest,47.497912,19.040235");
        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat());
        assertEquals(19.040235, location.getLon());
    }

    @Test
    @DisplayName("Testing location on equator")
    void testIsOnEquator() {
        Location equatorLocation = new Location("Equator", 0.0, 38.326545);
        Location notEquatorLocation = new Location("notEquator", 0.0212, 38.326545);


        assertTrue(locationParser.isOnEquator(equatorLocation));
        assertFalse(locationParser.isOnEquator(notEquatorLocation));
    }

    private Object[][] values = {{new Location("Equator", 0.0, 38.326545), true}, {new Location("AlsoOnEquator", 0.0, 131.326545), true}, {new Location("NotOnEquator", 9.7894, 38.326545), false}};

    @RepeatedTest(3)
    @DisplayName("Testing location on equator")
    void testIsOnEquator(RepetitionInfo repetitionInfo) {

        assertEquals(values[repetitionInfo.getCurrentRepetition() - 1][1], locationParser.isOnEquator((Location) values[repetitionInfo.getCurrentRepetition() - 1][0]));
    }



//    Teszteld az isOnEquator() metódust! Vegyél fel egy tömbbe pár Location objektumot,
//    ezek egy része az egyenlítőn legyen! Tömbök tömbjét használj,
//    azaz vegyél fel egy boolean értéket is,
//    hogy mely esetén kell a tesztelendő metódusnak true értéket visszaadnia!
//
//    Ismétlődő tesztekkel menj végig a tömbön,
//    hívd meg az adott Location isOnEquator() metódusát, és vizsgáld,
//    hogy a mellette megadott boolean értéket adja-e vissza.

    @Test
    @DisplayName("Testing location on prime meridian")
    void testIsOnPrimeMeridian() {
        Location onMeridianLocation = new Location("Meridian", 47.497912, 0.0);
        Location notOnMeridianLocation = new Location("notOnMeridian", 0.0212, 38.326545);

        assertTrue(locationParser.isOnPrimeMeridian(onMeridianLocation));
        assertFalse(locationParser.isOnPrimeMeridian(notOnMeridianLocation));
    }

    @Test
    void testNewInstance() {
        assertNotSame(locationParser.parse("Budapest,47.497912,19.040235"), locationParser.parse("Budapest,47.497912,19.040235"));
    }

    @Test
    void testSameLocation() {
        assertEquals(locationParser.parse("Budapest,47.497912,19.040235"), locationParser.parse("Budapest,47.497912,19.040235"));
    }

    @Test
    void testDistanceFrom() {
        Location budapestLocation = new Location("Budapest", 47.497912, 19.040235, 105);
        Location debrecenLocation = new Location("Debrecen", 47.53, 21.639167, 121);
        Location szegedLocation = new Location("Szeged", 46.25, 20.166667, 75);

        assertEquals(195, (int)(budapestLocation.distanceFrom(debrecenLocation)));
        assertEquals(165, (int)(budapestLocation.distanceFrom(szegedLocation)));
        assertEquals(186, (int)(debrecenLocation.distanceFrom(szegedLocation)));
    }

    @Test
    void testWrongLatitude() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Location("IlyenNincs", -100.1023, 85.222));
        assertEquals("Latitude must be between -90 and 90!", ex.getMessage());

        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> new Location("IlyenSe", 101.1023, 95.222));
        assertEquals("Latitude must be between -90 and 90!", ex1.getMessage());
    }

    @Test
    void testWrongLongitude() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Location("IlyenNincs", -10.1023, 185.222));
        assertEquals("Longitude must be between -180 and 180!", ex.getMessage());

        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> new Location("IlyenSe", 11.1023, -195.222));
        assertEquals("Longitude must be between -180 and 180!", ex1.getMessage());
    }


}