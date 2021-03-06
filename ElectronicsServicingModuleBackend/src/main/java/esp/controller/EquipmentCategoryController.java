package esp.controller;

import esp.service.EquipmentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static esp.config.WebSecurityConfig.FRONTEND_URL;

@CrossOrigin(origins = FRONTEND_URL)
@RestController
@RequestMapping("/category")
public class EquipmentCategoryController {

	private final EquipmentCategoryService equipmentCategoryService;

	@Autowired
	public EquipmentCategoryController(EquipmentCategoryService equipmentCategoryService) {
		this.equipmentCategoryService = equipmentCategoryService;
	}

	@GetMapping
	public List<String> getAll() {
		return equipmentCategoryService.getAll();
	}

}
