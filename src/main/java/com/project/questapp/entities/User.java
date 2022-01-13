package com.project.questapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String userName;
	String password;
	int avatar;
}
