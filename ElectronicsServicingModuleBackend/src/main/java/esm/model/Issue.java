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
public class Issue {

    public enum Status { ACTIVE, RESOLVED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Column
    private String description;

    @NotNull
    private Status status;

}
