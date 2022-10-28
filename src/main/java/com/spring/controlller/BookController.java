package com.spring.controlller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.BookRepo;
import com.spring.entity.Book;
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	public BookRepo rep;
	
	@PostMapping
	public Book save (@RequestBody Book book) {
		return rep.save(book);
	}
	
	@GetMapping
	public List<Book> get () {
		return rep.findAll();
	}
	
	@GetMapping("/{id}")
	public Book get (@PathVariable int id) {
		Optional<Book> employee = rep.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		}
		throw new RuntimeException("Not found for the id "+id);
	}
	
	@PutMapping("/{id}")
	public Book update (@PathVariable int id, @RequestBody Book book) {
		book.setId(id);
		return rep.save(book);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete (@PathVariable int id) {
		rep.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}

