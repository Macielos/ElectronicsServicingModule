package esp.service;

import esp.model.EquipmentCategory;
import esp.repository.EquipmentCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EquipmentCategoryService {

	private final EquipmentCategoryRepository equipmentCategoryRepository;

	@Autowired
	public EquipmentCategoryService(EquipmentCategoryRepository equipmentCategoryRepository) {
		this.equipmentCategoryRepository = equipmentCategoryRepository;
	}

	public List<String> getAll() {
		return equipmentCategoryRepository.findAll().stream()
				.map(EquipmentCategory::getName)
				.collect(Collectors.toList());
	}
}
