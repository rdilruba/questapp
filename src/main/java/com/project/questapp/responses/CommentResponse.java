package com.project.questapp.responses;

import com.project.questapp.entities.Comment;

import lombok.Data;

@Data
public class CommentResponse {
	
	Long id;
	Long userId;
	String userName;
	String text;

	public CommentResponse(Comment entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUserName();
		this.text = entity.getText();
	}
}
