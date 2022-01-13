package com.project.questapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.questapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

	@Query(value = "select id from post where user_id = :userId order by create_date desc limit 5", 
			nativeQuery = true)
	List<Long> findTopByUserId(@Param("userId") Long userId);
}
