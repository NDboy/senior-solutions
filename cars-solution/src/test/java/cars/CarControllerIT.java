package cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CarControllerIT {

    @Autowired
    CarController carController;

    @Test
    void testGetCars() {
        List<CarDto> dtos = carController.getCars();

        assertThat(dtos).hasSize(5).extracting(CarDto::getType).contains("Mondeo", "Vectra", "Escort", "Insignia");
        assertThat(dtos).hasSize(5).extracting(CarDto::getYear).contains(2001,2002,2010,2011,2014);
    }

    @Test
    void testGetBrands() {
        List<String> brands = carController.getBrands();

        assertThat(brands).hasSize(2).contains("Opel", "Ford");

    }

}
