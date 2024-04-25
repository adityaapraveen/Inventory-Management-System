package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class BookController {

	@Autowired
	private BookService service;

	@Autowired
	private MyBookListService myBookService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}

	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book> list = service.getAllBook();
		// ModelAndView m=new ModelAndView();
		// m.setViewName("bookList");
		// m.addObject("book",list);
		return new ModelAndView("bookList", "book", list);
	}

	// @PostMapping("/save")
	// public String addBook(@ModelAttribute Book b) {
	// service.save(b);
	// return "redirect:/available_books";
	// }
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list = myBookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}

	@RequestMapping("/mylist/{product_id}")
	public String getMyList(@PathVariable("product_id") int product_id) {
		Book b = service.getBookById(product_id);
		MyBookList mb = new MyBookList(b.getProduct_id(), b.getName(), b.getQuantity(), b.getPrice(),
				b.getExpiryDate());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}

	// @RequestMapping("/editBook/{product_id}")
	// public String editBook(@PathVariable("product_id") int product_id,Model
	// model) {
	// Book b=service.getBookById(product_id);
	// model.addAttribute("book",b);
	// return "bookEdit";

	@RequestMapping("/deleteBook/{product_id}")
	public String deleteBook(@PathVariable("product_id") int product_id) {
		service.deleteById(product_id);
		return "redirect:/available_books";
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b, @RequestParam("expiry_date") String expiryDate) {
		// Parse the expiry date from String to Date
		if (expiryDate != null && !expiryDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsedDate = dateFormat.parse(expiryDate);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
				b.setExpiryDate(sqlDate);
			} catch (ParseException e) {
				// Handle parsing exception if necessary
				e.printStackTrace();
			}
		}
		service.save(b);
		return "redirect:/available_books";
	}

	@RequestMapping("/editBook/{product_id}")
	public String editBook(@PathVariable("product_id") int product_id, Model model) {
		Book b = service.getBookById(product_id);
		model.addAttribute("book", b);
		return "bookEdit";
	}

	@PostMapping("/updateBook")
	public String updateBook(@ModelAttribute Book b, @RequestParam("expiry_date") String expiryDate) {
		// Parse the expiry date from String to Date
		if (expiryDate != null && !expiryDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(expiryDate);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
				b.setExpiryDate(sqlDate);
			} catch (ParseException e) {
				// Handle parsing exception if necessary
				e.printStackTrace();
			}
		}
		service.save(b);
		return "redirect:/available_books";
	}

}
