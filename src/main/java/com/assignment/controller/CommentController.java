package com.assignment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.assignment.model.Comment;
import com.assignment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentRepository commentRepository;

	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> getAllcomments(@RequestParam(required = false) String title) {
		try {
			List<Comment> comments = new ArrayList<Comment>();

			if (title == null)
				commentRepository.findAll().forEach(comments::add);
			else
				commentRepository.findByTitleContaining(title).forEach(comments::add);

			if (comments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(comments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/comments/{id}")
	public ResponseEntity<Comment> getcommentById(@PathVariable("id") long id) {
		Optional<Comment> commentData = commentRepository.findById(id);

		if (commentData.isPresent()) {
			return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/comments")
	public ResponseEntity<Comment> createcomment(@RequestBody Comment comment) {
		try {
			comment.setPublishedDate(new Date());
			Comment _comment = commentRepository
					.save(comment);
			return new ResponseEntity<>(_comment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/comments/{id}")
	public ResponseEntity<Comment> updatecomment(@PathVariable("id") long id, @RequestBody Comment comment) {
		Optional<Comment> commentData = commentRepository.findById(id);

		if (commentData.isPresent()) {
			Comment _comment = commentData.get();
			_comment.setTitle(comment.getTitle());
			_comment.setDescription(comment.getDescription());
			//_comment.setPublished(comment.isPublished());
			return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity<HttpStatus> deletecomment(@PathVariable("id") long id) {
		try {
			commentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/comments")
	public ResponseEntity<HttpStatus> deleteAllcomments() {
		try {
			commentRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	@GetMapping("/comments/published")
//	public ResponseEntity<List<Comment>> findByPublished() {
//		try {
//			List<Comment> comments = commentRepository.findByPublished(true);
//
//			if (comments.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(comments, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
