package esp.controller;

import esp.dto.IssueDto;
import esp.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static esp.config.WebSecurityConfig.BACKEND_URL;

@CrossOrigin(origins = BACKEND_URL)
@RestController
@RequestMapping("/issue")
public class IssueController {

	private final IssueService issueService;

	@Autowired
	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public IssueDto startOrUpdateIssue(@RequestBody IssueDto issueDto, @RequestParam long equipmentId) {
		return issueService.startOrUpdateIssue(equipmentId, issueDto);
	}
}
