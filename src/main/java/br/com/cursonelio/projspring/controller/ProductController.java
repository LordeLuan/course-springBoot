package br.com.cursonelio.projspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursonelio.projspring.entity.Product;
import br.com.cursonelio.projspring.services.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findByID(@PathVariable Long id){
		Product obj = service.findByID(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
