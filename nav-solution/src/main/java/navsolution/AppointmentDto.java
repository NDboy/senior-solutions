package navsolution;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentDto {

    @IsValidTaxNumber
    private String taxId;

    @IsValidIntervall
    private Intervall intervall;

    @IsValidType
    private String typeCode;
}
