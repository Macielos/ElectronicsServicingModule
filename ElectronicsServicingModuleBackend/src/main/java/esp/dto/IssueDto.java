package esp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {

	public static final String PENDING = "PENDING";
	public static final String RESOLVED = "RESOLVED";

	private Long id;
	private String title;
	private String description;
	private String status;
	private Date creationDate;
}
