package com.example.mybookshelf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "books", schema="mybookshelf")
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** Serial型。自動採番 */
	private Long serialNo;
	
	/** 本のタイトル */
	private String title;
	
	/** 本の表紙URL */
	private String img;

	/** 本の読了日 */
	private String readAt;
	
	/** 本の評価 */
	private int review;
	
	/** 本のコメント */
	private String comments;
	
	/** uid: firebaseで一意なToken(usersテーブルの主キー) */
	private String userUid;
}

