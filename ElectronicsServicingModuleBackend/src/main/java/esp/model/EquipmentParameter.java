package esp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "equipment_parameter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentParameter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	@NotNull
	private Equipment equipment;

	@NotNull
	private String value;

}
