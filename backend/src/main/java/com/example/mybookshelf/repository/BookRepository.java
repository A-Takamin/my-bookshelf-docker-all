package com.example.mybookshelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mybookshelf.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByUserUid(String userUid);
}
