package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookStore.service.MyBookListService;

@Controller
public class MyBookListController {
	
	@Autowired
	private MyBookListService service;

	@RequestMapping("/deleteMyList/{product_id}")
	public String deleteMyList(@PathVariable("product_id") int product_id) {
		service.deleteById(product_id);
		return "redirect:/my_books";
	}
}
