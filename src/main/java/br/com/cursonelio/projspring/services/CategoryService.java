package br.com.cursonelio.projspring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursonelio.projspring.entity.Category;
import br.com.cursonelio.projspring.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findByID(Long id) {
	return repository.findById(id).get();
		
	}
}
