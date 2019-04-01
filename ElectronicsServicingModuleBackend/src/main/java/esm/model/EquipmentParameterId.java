package esm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentParameterId {

    @NotNull
    private Long equipmentId;

    @NotNull
    private String name;
}
