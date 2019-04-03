package esp.service;

import esp.dto.CommentDto;
import esp.dto.EquipmentDto;
import esp.dto.EquipmentParameterDto;
import esp.dto.IssueDto;
import esp.model.Comment;
import esp.model.Equipment;
import esp.model.EquipmentCategory;
import esp.model.EquipmentParameter;
import esp.model.Issue;
import esp.repository.EquipmentCategoryRepository;
import esp.repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EquipmentService {

	private final EquipmentRepository equipmentRepository;
	private final EquipmentCategoryRepository equipmentCategoryRepository;

	private Map<String, EquipmentCategory> categoriesByName;

	@Autowired
	public EquipmentService(EquipmentRepository equipmentRepository, EquipmentCategoryRepository equipmentCategoryRepository) {
		this.equipmentRepository = equipmentRepository;
		this.equipmentCategoryRepository = equipmentCategoryRepository;
	}

	@PostConstruct
	public void init() {
		if (categoriesByName == null) {
			log.info("Fetching categories...");
			categoriesByName = equipmentCategoryRepository.findAll().stream()
					.collect(Collectors.toMap(EquipmentCategory::getName, Function.identity()));
			log.info("Fetched {} categories", categoriesByName.size());
		}
	}

	public EquipmentDto getById(Long id) {
		log.debug("getById: {}", id);
		var equipment = equipmentRepository.findById(id).map(this::toDto).orElse(null);
		log.debug("equipment: {}", equipment);
		return equipment;
	}

	public List<EquipmentDto> getPage(Integer offset, Integer count) {
		log.debug("getPage");
		var equipments = equipmentRepository.findAll(PageRequest.of(Optional.ofNullable(offset).orElse(0), Optional.ofNullable(count).orElse(Integer.MAX_VALUE))).stream()
				.map(this::toDto)
				.collect(Collectors.toList());
		log.debug("equipments: {}", equipments);
		return equipments;
	}

	public EquipmentDto save(EquipmentDto equipmentDto) {
		log.debug("Save: {}", equipmentDto);
		return toDto(equipmentRepository.save(toEntity(equipmentDto)));
	}

	private EquipmentDto toDto(Equipment equipment) {
		return new EquipmentDto(equipment.getId(), equipment.getName(), equipment.getCategory().getName(),
				equipment.getParameters().stream()
						.map(equipmentParameter -> new EquipmentParameterDto(equipmentParameter.getName(), equipmentParameter.getValue()))
						.collect(Collectors.toList()),
				equipment.getIssues().stream()
						.map(issue -> new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus().toString(), issue.getCreationDate()))
						.collect(Collectors.toList()),
				equipment.getComments().stream()
						.map(comment -> new CommentDto(comment.getId(), comment.getAuthor(), comment.getComment(), comment.getPublicationDate()))
						.collect(Collectors.toList()));
	}

	private Equipment toEntity(EquipmentDto equipmentDto) {
		var equipment = new Equipment(equipmentDto.getId(), equipmentDto.getName(), categoriesByName.get(equipmentDto.getCategory()),
				equipmentDto.getParameters().stream()
						.map(equipmentParameterDto -> new EquipmentParameter(equipmentDto.getId(), equipmentParameterDto.getName(), null, equipmentParameterDto.getValue()))
						.collect(Collectors.toList()),
				equipmentDto.getIssues().stream()
						.map(issueDto -> new Issue(issueDto.getId(), null, issueDto.getTitle(), issueDto.getDescription(), Issue.Status.valueOf(issueDto.getStatus()), issueDto.getCreationDate()))
						.collect(Collectors.toList()),
				equipmentDto.getComments().stream()
						.map(commentDto -> new Comment(commentDto.getId(), null, commentDto.getAuthor(), commentDto.getComment(), commentDto.getPublicationDate()))
						.collect(Collectors.toList()));
		equipment.getParameters().forEach(equipmentParameter -> equipmentParameter.setEquipment(equipment));
		equipment.getIssues().forEach(issue -> issue.setEquipment(equipment));
		equipment.getComments().forEach(comment -> comment.setEquipment(equipment));
		return equipment;
	}
}
