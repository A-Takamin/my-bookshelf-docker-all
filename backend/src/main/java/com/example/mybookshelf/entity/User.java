package com.example.mybookshelf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	/** Serial型。自動採番 */
//	private Long serialNo;
	
	@Id
	/** uid: firebaseで一意なToken */
	private String uid;
}
