package esp.controller;

import esp.dto.CommentDto;
import esp.model.Comment;
import esp.model.Equipment;
import esp.model.EquipmentCategory;
import esp.repository.CommentRepository;
import esp.service.CommentService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Date;

public class CommentControllerTest extends BaseTest {

	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void testAddComment() {
		equipmentCategoryRepository.save(new EquipmentCategory(null, "AGD"));
		EquipmentCategory category = first(equipmentCategoryRepository);
		Assert.assertNotNull(category);
		Assert.assertEquals("AGD", category.getName());

		equipmentRepository.save(new Equipment(null, "ASUS", category, Collections.emptyList(), Collections.emptyList(), Collections.emptyList()));
		Equipment equipment = first(equipmentRepository);
		Assert.assertNotNull(equipment);
		Assert.assertEquals("ASUS", equipment.getName());

		CommentController commentController = new CommentController(new CommentService(equipmentRepository, commentRepository));
		Date date = new Date();
		commentController.addComment(new CommentDto(1L, "marik1234", "pralka nie dziala", date), equipment.getId());

		Comment comment = first(commentRepository);
		Assert.assertNotNull(comment);
		Assert.assertEquals("marik1234", comment.getAuthor());
		Assert.assertEquals("pralka nie dziala", comment.getComment());
		Assert.assertEquals(date, comment.getPublicationDate());

		clear();
	}
}