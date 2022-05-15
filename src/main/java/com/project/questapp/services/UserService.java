package com.project.questapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.User;
import com.project.questapp.repos.CommentRepository;
import com.project.questapp.repos.LikeRepository;
import com.project.questapp.repos.PostRepository;
import com.project.questapp.repos.UserRepository;

@Service
public class UserService {

	UserRepository userRepository;
	LikeRepository likeRepository;
	CommentRepository commentRepository;
	PostRepository postRepository;

	public UserService(UserRepository userRepository, LikeRepository likeRepository, 
			CommentRepository commentRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.likeRepository = likeRepository;
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateOneUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			foundUser.setAvatar(newUser.getAvatar());
			userRepository.save(foundUser);
			return foundUser;
		}else
			return null;
	}

	public void deleteById(Long userId) {
		try {
		userRepository.deleteById(userId);
		}catch(EmptyResultDataAccessException e) { //user zaten yok, db'den empty result gelmiş
			System.out.println("User "+userId+" doesn't exist"); //istersek loglayabiliriz
		}
	}

	public User getOneUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public List<Object> getUserActivity(Long userId) {
		List<Long> postIds = postRepository.findTopByUserId(userId);
		if(postIds.isEmpty())
			return null;
		List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
		List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
		List<Object> result = new ArrayList<>();
		result.addAll(comments);
		result.addAll(likes);
		return result;
	}
	
	
}
