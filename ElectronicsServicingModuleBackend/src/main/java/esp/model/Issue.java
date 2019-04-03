package esp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "issue")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

	public enum Status {PENDING, RESOLVED}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	@NotNull
	private Equipment equipment;

	@NotNull
	private String title;

	@Column
	private String description;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Status status;

	@Column(name = "creation_date")
	@NotNull
	private Date creationDate;

}
