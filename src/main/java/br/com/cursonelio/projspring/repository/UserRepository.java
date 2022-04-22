package br.com.cursonelio.projspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursonelio.projspring.entity.User;

@Repository
public interface  UserRepository extends JpaRepository<User, Long>{

}
