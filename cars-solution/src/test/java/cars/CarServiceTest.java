package cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarServiceTest {


    private CarService carService;

    @BeforeEach
    void init() {
        carService = new CarService(new ModelMapper());
    }


    @Test
    void testListCars() {
        List<CarDto> cars = carService.listCars();
        assertThat(cars).hasSize(5).extracting(CarDto::getType).contains("Focus", "Mondeo", "Escort", "Vectra", "Insignia");
    }

    @Test
    void testListBrands() {
        List<String> brands = carService.listBrands();
        assertThat(brands).hasSize(2).contains("Ford", "Opel");
    }


}