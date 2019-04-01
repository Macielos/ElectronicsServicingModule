package esm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Equipment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentParameter {

    @EmbeddedId
    private EquipmentParameterId equipmentParameterId;

    @NotNull
    private String value;

}
