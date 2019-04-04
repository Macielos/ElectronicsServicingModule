package esp.controller;

import esp.dto.CommentDto;
import esp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static esp.config.WebSecurityConfig.FRONTEND_URL;

@CrossOrigin(origins = FRONTEND_URL)
@RestController
@RequestMapping("/comment")
public class CommentController {

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentDto addComment(@RequestBody CommentDto comment, @RequestParam long equipmentId) {
		return commentService.addComment(equipmentId, comment);
	}
}
