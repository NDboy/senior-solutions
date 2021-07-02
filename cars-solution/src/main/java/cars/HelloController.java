package cars;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public String sayHello() {
        return helloService.sayHello();
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

