package cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;


    @Test
    void getCars() {
        List<KmState> states = new ArrayList<>();
        when(carService.listCars()).thenReturn(new ArrayList<>(List.of(
                new CarDto("Ford", "Focus", 2010, Condition.NORMAL, states),
                new CarDto("Ford", "Mondeo", 2011, Condition.WRONG, states),
                new CarDto("Ford", "Escort", 2001, Condition.WRONG, states),
                new CarDto("Opel", "Vectra", 2002, Condition.WRONG, states),
                new CarDto("Opel", "Insignia", 2014, Condition.EXCELLENT, states)
        )));
        List<CarDto> dtos = carController.getCars();
        assertThat(dtos).hasSize(5).extracting(CarDto::getType).contains("Mondeo", "Vectra");

        verify(carService).listCars();

    }

    @Test
    void getBrands() {
        when(carService.listBrands()).thenReturn(new ArrayList<>(List.of("Ford", "Opel")));
        List<String> brands = carController.getBrands();

        assertThat(brands).hasSize(2).contains("Ford", "Opel");
        verify(carService).listBrands();

    }
}