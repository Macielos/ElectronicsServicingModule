package esp.controller;

import esp.dto.IssueDto;
import esp.model.Equipment;
import esp.model.EquipmentCategory;
import esp.model.Issue;
import esp.repository.IssueRepository;
import esp.service.IssueService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Date;

public class IssueControllerTest extends BaseTest {

	@Autowired
	private IssueRepository issueRepository;

	@Test
	public void testStartOrUpdateIssue() {
		equipmentCategoryRepository.save(new EquipmentCategory(null, "AGD"));
		EquipmentCategory category = first(equipmentCategoryRepository);
		Assert.assertNotNull(category);
		Assert.assertEquals("AGD", category.getName());

		equipmentRepository.save(new Equipment(null, "ASUS", category, Collections.emptyList(), Collections.emptyList(), Collections.emptyList()));
		Equipment equipment = first(equipmentRepository);
		Assert.assertNotNull(equipment);
		Assert.assertEquals("ASUS", equipment.getName());

		IssueController issueController = new IssueController(new IssueService(equipmentRepository, issueRepository));
		Date date = new Date();
		issueController.startOrUpdateIssue(new IssueDto(1L, "pralka nie dziala", "no nie dziala", Issue.Status.PENDING.toString(), date), equipment.getId());

		Issue issue = first(issueRepository);
		Assert.assertNotNull(issue);
		Assert.assertEquals("pralka nie dziala", issue.getTitle());
		Assert.assertEquals("no nie dziala", issue.getDescription());
		Assert.assertEquals(date, issue.getCreationDate());

		clear();
	}
}