package com.example.mybookshelf.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class BookForm {

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
}
