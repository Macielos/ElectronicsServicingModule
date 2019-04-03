package esp.service;

import esp.dto.IssueDto;
import esp.model.Issue;
import esp.repository.EquipmentRepository;
import esp.repository.IssueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IssueService {

	private final EquipmentRepository equipmentRepository;
	private final IssueRepository issueRepository;

	@Autowired
	public IssueService(EquipmentRepository equipmentRepository, IssueRepository issueRepository) {
		this.equipmentRepository = equipmentRepository;
		this.issueRepository = issueRepository;
	}

	public IssueDto startOrUpdateIssue(long equipmentId, IssueDto issueDto) {
		log.debug("Save: {}", issueDto);
		return toDto(issueRepository.save(toEntity(equipmentId, issueDto)));
	}

	private IssueDto toDto(Issue issue) {
		return new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus().toString(), issue.getCreationDate());
	}

	private Issue toEntity(long equipmentId, IssueDto issueDto) {
		return new Issue(issueDto.getId(), equipmentRepository.findById(equipmentId).orElseThrow(() -> new RuntimeException("No equipment with id "+equipmentId)), issueDto.getTitle(),
				issueDto.getDescription(), Issue.Status.valueOf(issueDto.getStatus()), issueDto.getCreationDate());
	}
}
