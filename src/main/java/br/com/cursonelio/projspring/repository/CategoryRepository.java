package br.com.cursonelio.projspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursonelio.projspring.entity.Category;

@Repository //Opcional o JPA já está mapeado no Spring boot
public interface  CategoryRepository extends JpaRepository<Category, Long>{

}
