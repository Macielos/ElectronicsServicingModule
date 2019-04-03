package esp.controller;

import esp.dto.EquipmentDto;
import esp.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static esp.config.WebSecurityConfig.BACKEND_URL;

@CrossOrigin(origins = BACKEND_URL)
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;

	@GetMapping(params = {"offset", "size"})
	public List<EquipmentDto> getPage(Integer offset, Integer size) {
		return equipmentService.getPage(offset, size);
	}

	@GetMapping(params = "id")
	public EquipmentDto getById(Long id) {
		return equipmentService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EquipmentDto create(@RequestBody EquipmentDto equipmentDto) {
		return equipmentService.save(equipmentDto);
	}
}
