package navsolution;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NavService {



    private List<Type> types = Arrays.asList(new Type("001", "Adóbevallás"), new Type("002", "Befizetés"));

    public List<Type> getTypes() {
        return types;
    }

    public boolean hasValidType(String value) {
        return types.stream()
                .anyMatch(t -> t.getCode().equals(value));
    }

    public AppointmentDto createAppointment(CreateAppointmentCommand command) {
        AppointmentDto dto = new AppointmentDto(command.getTaxId(), command.getIntervall(), command.getTypeCode());
        return dto;
    }
}
