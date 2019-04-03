package esp.service;

import esp.dto.CommentDto;
import esp.model.Comment;
import esp.repository.CommentRepository;
import esp.repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommentService {

	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private CommentRepository commentRepository;

	public CommentDto addComment(long equipmentId, CommentDto commentDto) {
		log.debug("Save: {} {}", equipmentId, commentDto);
		return toDto(commentRepository.save(toEntity(equipmentId, commentDto)));
	}

	private CommentDto toDto(Comment comment) {
		return new CommentDto(comment.getId(), comment.getAuthor(), comment.getComment(), comment.getPublicationDate());
	}

	private Comment toEntity(long equipmentId, CommentDto commentDto) {
		return new Comment(commentDto.getId(), equipmentRepository.findById(equipmentId).orElseThrow(() -> new RuntimeException("No equipment with id "+equipmentId)), commentDto.getAuthor(), commentDto.getComment(), commentDto.getPublicationDate());
	}
}
