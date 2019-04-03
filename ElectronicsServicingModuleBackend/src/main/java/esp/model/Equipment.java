package esp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotNull
	private EquipmentCategory category;

	@Cascade(value = CascadeType.ALL)
	@OneToMany(mappedBy = "equipment", orphanRemoval = true)
	private List<EquipmentParameter> parameters = new ArrayList<>();

	@Cascade(value = CascadeType.ALL)
	@OneToMany(mappedBy = "equipment")
	private List<Issue> issues = new ArrayList<>();

	@Cascade(value = CascadeType.ALL)
	@OneToMany(mappedBy = "equipment")
	private List<Comment> comments = new ArrayList<>();

}
