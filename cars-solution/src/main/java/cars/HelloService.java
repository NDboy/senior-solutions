package cars;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello() {
        return "Üdvözlünk az oldalon!";
    }

}


//        Legyen egy `CarService` osztály mely legyen már Spring komponens.
//        Ebben legyen a kocsik listája néhány beégetett adattal.

//        Legyen egy `CarController` osztály mely szintén Spring komponens.

//        Legyen még egy `HelloController` és `HelloService` osztályod is,
//        mely a kezdő oldal feladatot oldja meg.

//        Ezen struktúra segítségével oldd meg a következő feladatokat:
//        + Alkalmazás indításakor a böngészőben jelenjen meg
//        az "Üdvölünk az oldalon" szöveg!
//        + /cars url-n keresztül jelenjen meg az összes autó.
//        + /types végponton keresztül jelenjenek meg a listában található márkák.

//        ## Tesztelés

//        + Készíts unit tesztet a CarService osztály tesztelésre!
//        + A CarController osztályt kétféle képpen tesztelted:
//        1. Unit teszt ahol a CarService osztályt mockolod
//        2. Integrációs teszt ahol beindítod a Springet
//        és úgy teszteled a működést.

