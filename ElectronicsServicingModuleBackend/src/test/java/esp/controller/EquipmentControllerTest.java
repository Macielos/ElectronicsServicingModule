package esp.controller;

import esp.dto.CommentDto;
import esp.dto.EquipmentDto;
import esp.dto.EquipmentParameterDto;
import esp.dto.IssueDto;
import esp.model.EquipmentCategory;
import esp.service.EquipmentService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;

public class EquipmentControllerTest extends BaseTest {

	@Test
	public void testCreateEquipment() {
		equipmentCategoryRepository.save(new EquipmentCategory(null, "AGD"));
		EquipmentCategory category = first(equipmentCategoryRepository);
		Assert.assertNotNull(category);
		Assert.assertEquals("AGD", category.getName());

		EquipmentService equipmentService = new EquipmentService(equipmentRepository, equipmentCategoryRepository);
		equipmentService.init();
		EquipmentController equipmentController = new EquipmentController(equipmentService);

		EquipmentDto equipmentDto = new EquipmentDto(null, "Pralka", "AGD",
				Collections.singletonList(new EquipmentParameterDto("Max obroty", "1200")),
				Collections.singletonList(new IssueDto(null, "zepsuta", null, "PENDING", new Date())),
				Collections.singletonList(new CommentDto(null, "janusz123", "Nie dziala", new Date()))
		);

		EquipmentDto created = equipmentController.create(equipmentDto);
		Assert.assertNotNull(created.getId());

		EquipmentDto fetched = equipmentController.getById(created.getId());

		Assert.assertNotNull(fetched);
		Assert.assertEquals("Pralka", fetched.getName());
		Assert.assertEquals("AGD", fetched.getCategory());
		Assert.assertEquals(1, fetched.getParameters().size());
		Assert.assertEquals(1, fetched.getIssues().size());
		Assert.assertEquals(1, fetched.getComments().size());

		clear();
	}
}