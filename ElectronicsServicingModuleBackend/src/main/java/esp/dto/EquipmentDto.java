package esp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {

	private Long id;
	private String name;
	private String category;
	private List<EquipmentParameterDto> parameters;
	private List<IssueDto> issues;
	private List<CommentDto> comments;

}
