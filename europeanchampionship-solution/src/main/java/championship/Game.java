package championship;


public class Game {

    private String firstCountry;
    private String secondCountry;
    private int firstCountryScore;
    private int secondCountryScore;

    public Game() {
    }

    public Game(String firstCountry, String secondCountry, int firstCountryScore, int secondCountryScore) {
        this.firstCountry = firstCountry;
        this.secondCountry = secondCountry;
        this.firstCountryScore = firstCountryScore;
        this.secondCountryScore = secondCountryScore;
    }

    public String getTheWinner() {
        if (firstCountryScore > secondCountryScore) {
            return firstCountry;
        } else if (firstCountryScore < secondCountryScore) {
            return secondCountry;
        }
        return "draw";
    }

    public String getFirstCountry() {
        return firstCountry;
    }

    public void setFirstCountry(String firstCountry) {
        this.firstCountry = firstCountry;
    }

    public String getSecondCountry() {
        return secondCountry;
    }

    public void setSecondCountry(String secondCountry) {
        this.secondCountry = secondCountry;
    }

    public int getFirstCountryScore() {
        return firstCountryScore;
    }

    public void setFirstCountryScore(int firstCountryScore) {
        this.firstCountryScore = firstCountryScore;
    }

    public int getSecondCountryScore() {
        return secondCountryScore;
    }

    public void setSecondCountryScore(int secondCountryScore) {
        this.secondCountryScore = secondCountryScore;
    }

    @Override
    public String toString() {
        return "Game{" +
                "firstCountry='" + firstCountry + '\'' +
                ", secondCountry='" + secondCountry + '\'' +
                ", firstCountryScore=" + firstCountryScore +
                ", secondCountryScore=" + secondCountryScore +
                '}';
    }
}

//## Feladat
//        A mai feladatban az EB meccsek eredményeit kell egy alkalmazásban
//        tárolnod, és különböző feladatokat elvégezned.
//        ### championship.Game
//        Legyen egy `championship.Game` nevű osztályod a következő attribútumokkal
//        + `firstCountry (String)`
//        + `secondCountry (String)`
//        + `firstCountryScore (int)`
//        + `secondCountryScore (int)`

//        Legyen benne egy metódus ami visszaadja a győztes ország nevét!


//        ### GameRepository
//        Legyen egy `GameRepository` nevű osztályod, melynek van egy meccseket
//        memóriában tároló listája van.

//        A listához elemet két féle képpen lehet hozzáadni. Vagy egy `addGame(championship.Game game)` metódussal,
//        vagy fájlból beolvasva, ahol a fájl egy csv állomány.

//        ### GameService
//        Legyen egy `GameService` nevű osztályod, ami különböző statisztikai adatokat jelenít meg.
//        Legyen egy `GameRepository` attribútuma amin keresztül eléri a benne lévő listát.

//        Megvalósítandó metódusok:

//        + Határozd meg a legnagyobb gólkülönbséggel véget ért mérkőzést
//        + Határozd meg hogy egy paraméterül kapott ország hány gólt rúgott eddig
//        + Határozd meg az eddig legtöbb gólt rúgó országot


//        ### Tesztelés
//        Mindegyik osztályhoz legyen külön teszt osztály. A nem generált metódusokhoz legyen teszt eset, lehetőleg több.
//        A `GameService` osztály második metódusát paraméterezett teszttel végezd. Ez lehet akár dinamikus teszteset is.
