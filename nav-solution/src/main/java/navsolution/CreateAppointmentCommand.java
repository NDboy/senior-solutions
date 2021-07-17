package navsolution;

import lombok.Data;

@Data
public class CreateAppointmentCommand {

    @IsValidTaxNumber
    private String taxId;

    @IsValidIntervall
    private Intervall intervall;

    @IsValidType
    private String typeCode;

}
