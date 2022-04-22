package br.com.cursonelio.projspring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursonelio.projspring.entity.User;
import br.com.cursonelio.projspring.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findByID(Long id) {
	return repository.findById(id).get();
		
	}
}
