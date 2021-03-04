package com.example.mybookshelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybookshelf.entity.Book;
import com.example.mybookshelf.form.BookForm;
import com.example.mybookshelf.service.BookService;
import com.example.mybookshelf.service.UserService;

@RestController
@CrossOrigin
public class AppController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	/* ＝＝＝＝＝＝ ユーザ操作 ＝＝＝＝＝＝ */
	/**
	 * ユーザ作成
	 * @return
	 */
	@PostMapping("/user/{uid}")
	public String createUser(@PathVariable("uid") String uid, @RequestBody UserForm userForm) {
		userService.createUser(uid, userForm);
		return "success1";
	}
//	/**
//	 * ユーザ修正
//	 * @return
//	 */
//	@PutMapping("/user/{uid}")
//	public String modifyUser(@PathVariable("uid") String uid, @RequestBody UserForm userForm) {
//		userService.modifyUser(uid, userForm);
//		return "success2";
//	}
//	/**
//	 * ユーザ削除
//	 * @return
//	 */
//	@DeleteMapping("/user/{uid}")
//	public String deleteUser(@PathVariable("uid") String uid, @RequestBody UserForm userForm) {
//		return "success3";
//	}
	
	/* ＝＝＝＝＝＝ 本操作 ＝＝＝＝＝＝ */

	/**
	 * 本作成
	 * @return
	 */
	@PostMapping("/book/{uid}")
	public String createBook(@PathVariable("uid") String uid, @RequestBody BookForm bookForm) {
		bookService.createBook(bookForm, uid);
		return "success4";
	}
	/**
	 * 本全部取得
	 * @return
	 */
	@GetMapping("/book/{uid}")
	public List<Book> getBook(@PathVariable("uid") String uid) {
		return bookService.getBook(uid);
	}
	/**
	 * 本1つ取得
	 * @return
	 */
	@GetMapping("/book/{uid}/{bookNo}")
	public Book getOneBook(@PathVariable("uid") String uid, @PathVariable("bookNo") long bookNo) {
		return bookService.getOneBook(uid, bookNo);
	}
	/**
	 * 本修正
	 * @return
	 */
	@PutMapping("/book/{uid}/{bookNo}")
	public String updateBook(@PathVariable("uid") String uid, @PathVariable("bookNo") long bookNo, @RequestBody BookForm bookForm) {
		bookService.updateBook(bookForm, uid, bookNo);
		return "success";
	}
	/**
	 * 本削除
	 * @return
	 */
	@DeleteMapping("/book/{uid}/{bookNo}")
	public String deleteBook(@PathVariable("uid") String uid, @PathVariable("bookNo") long bookNo) {
		bookService.deleteBook(uid, bookNo);
		return "success7";
	}


}
