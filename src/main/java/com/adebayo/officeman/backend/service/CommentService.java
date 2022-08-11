package com.adebayo.officeman.backend.service;

import com.adebayo.officeman.backend.entity.Comment;
import com.adebayo.officeman.backend.entity.Project;
import com.adebayo.officeman.backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	@Autowired
	public CommentService (CommentRepository commentRepository){
		this.commentRepository = commentRepository;
	}

	public void save(Comment comment){
		commentRepository.save(comment);
	}

	public void deleteComment(Comment comment){
		commentRepository.delete(comment);
	}

	public void deleteAll(){
		commentRepository.deleteAll();
	}

//	public Set<Comment> findByProject(Project project){
////		return commentRepository.findCommentByProject(project);
//		return search(project);
//	}

	private Set<Comment> search(Project project){
		return commentRepository.search(project.getId());
	}


	public String getCommentContentByObject(Comment comment ) {
		if(comment == null){
			return " ";
		}
		return commentRepository.findCommentById(comment.getId()).getContent();

//		comment =	commentRepository.findCommentById(comment.getId());
//		if(comment == null){
//			return " ";
//		}
//		return comment.getContent();
	}
	public Comment findCommentById(Integer id){
		if(id == null){
			return new Comment();
		}
		return commentRepository.findCommentById(id);
	}
	public Comment findByProject(Project project){
		Comment comment = commentRepository.findCommentByProject(project);
		if(comment == null){
			return new Comment();
		}
		return comment;
	}

}