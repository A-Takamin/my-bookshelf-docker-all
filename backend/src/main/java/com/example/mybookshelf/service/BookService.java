package com.example.mybookshelf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mybookshelf.entity.Book;
import com.example.mybookshelf.form.BookForm;
import com.example.mybookshelf.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepo;

	/**
	 * 本を新たに作成する
	 * @param bookForm
	 * @param uid
	 */
	public void createBook(BookForm bookForm, String uid) {
		Book book = new Book();
		
		book.setTitle(bookForm.getTitle());
		book.setImg(bookForm.getImg());
		book.setReadAt(bookForm.getReadAt());
		book.setReview(bookForm.getReview());
		book.setComments(bookForm.getComments());
		book.setUserUid(uid);
		
		bookRepo.save(book);
	}
	
	/**
	 * 同じUIDを持つ本をすべて取得する
	 * @param uid
	 * @return
	 */
	public List<Book> getBook(String uid) {
		return bookRepo.findByUserUid(uid);
	}
	
	/**
	 * 本Noを持つ本を1つ取得する
	 * @param uid
	 * @param bookNo
	 * @return
	 */
	public Book getOneBook(String uid, long bookNo) {
		Optional<Book> opt = bookRepo.findById(bookNo);
		Book book = new Book();
		if(opt.isPresent()) {
			book = opt.get();
		}
		return book;
	}
	/**
	 * 同じ本IDを持つ本の情報を更新する
	 * @param bookForm
	 * @param uid
	 * @param bookNo
	 */
	public void updateBook(BookForm bookForm, String uid, long bookNo) {
		Book book = new Book();
		book.setSerialNo(bookNo);
		book.setTitle(bookForm.getTitle());
		book.setImg(bookForm.getImg());
		book.setReadAt(bookForm.getReadAt());
		book.setReview(bookForm.getReview());
		book.setComments(bookForm.getComments());
		book.setUserUid(uid);
		bookRepo.save(book);
	}
	/**
	 * 同じ本IDを持つ本を削除する
	 * @param uid
	 * @param bookNo
	 */
	public void deleteBook(String uid, long bookNo) {
		// uidいらないなぁ…REST設計…
		bookRepo.deleteById(bookNo);
	}
}
