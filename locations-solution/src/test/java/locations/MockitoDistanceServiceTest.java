package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockitoDistanceServiceTest {

    @Mock
    LocRepository repository;

    @InjectMocks
    DistanceService distanceService;

    @Test
    void testCalculateDistance() {
//        LocRepository repository = mock(LocRepository.class);
//        DistanceService distanceService = new DistanceService(repository);

        assertEquals(Optional.empty(), distanceService.calculateDistance("lkj", "lkj"));

        verify(repository, times(2)).findByName(any());
        verify(repository, times(2)).findByName(argThat(l -> l.equals("lkj")));
    }

    @Test
    void testCalculateDistance2() {
        when(repository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest", 47.497912, 19.040235, 105)));
        when(repository.findByName("Debrecen")).thenReturn(Optional.of(new Location("Debrecen", 47.53, 21.639167, 121)));

        assertEquals(195, (int)(distanceService.calculateDistance("Budapest", "Debrecen").get()).doubleValue());
        assertEquals(Optional.empty(), distanceService.calculateDistance("lkj", "lkj"));

    }

}