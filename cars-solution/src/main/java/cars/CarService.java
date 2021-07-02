package cars;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private List<Car> cars;

    private ModelMapper modelMapper;

    public CarService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        List<KmState> states = new ArrayList<>(List.of(
                new KmState(LocalDate.of(2018,1,1), 101235),
                new KmState(LocalDate.of(2018,1,2), 101535),
                new KmState(LocalDate.of(2018,1,3), 101815)
        ));
        cars = new ArrayList<>(List.of(
                new Car("Ford", "Focus", 2010, Condition.NORMAL, states),
                new Car("Ford", "Mondeo", 2011, Condition.WRONG, states),
                new Car("Ford", "Escort", 2001, Condition.WRONG, states),
                new Car("Opel", "Vectra", 2002, Condition.WRONG, states),
                new Car("Opel", "Insignia", 2014, Condition.EXCELLENT, states)
        ));
    }

    public List<CarDto> listCars() {
        Type targetListType = new TypeToken<List<CarDto>>(){}.getType();
        return modelMapper.map(cars, targetListType);
    }

    public List<String> listBrands() {
        return cars.stream()
                .map(Car::getBrand)
                .distinct()
                .toList();
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
