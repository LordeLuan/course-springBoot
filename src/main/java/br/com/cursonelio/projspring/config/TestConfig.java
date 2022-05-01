package br.com.cursonelio.projspring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cursonelio.projspring.entity.Category;
import br.com.cursonelio.projspring.entity.Order;
import br.com.cursonelio.projspring.entity.OrderItem;
import br.com.cursonelio.projspring.entity.Payment;
import br.com.cursonelio.projspring.entity.Product;
import br.com.cursonelio.projspring.entity.User;
import br.com.cursonelio.projspring.entity.enums.OrderStatus;
import br.com.cursonelio.projspring.repository.CategoryRepository;
import br.com.cursonelio.projspring.repository.OrderItemRepository;
import br.com.cursonelio.projspring.repository.OrderRepository;
import br.com.cursonelio.projspring.repository.ProductRepository;
import br.com.cursonelio.projspring.repository.UserRepository;

@Configuration
@Profile("test")
// Implementa uma interface com um metodo que roda ao iniciar o projeto
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRespository;
	
	@Autowired
	private OrderItemRepository orderItemRespository;

	@Override
	public void run(String... args) throws Exception {

//		Instancia os objetos
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
	
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
//		Passa os objetos para serem salvos na tabela do banco, com uma lista
		categoryRespository.saveAll(Arrays.asList(cat1,cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
//		Associação entre os objetos - Product e Category
		// adiciona o objeto cat2(categoria) ao objeto product (produto)
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
//		Instancia os objetos	
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

//		Instancia os objetos e passa o usuario que solicitou o pedido
		Order o1 = new Order(null, Instant.parse("2022-04-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2022-03-21T03:42:10Z"), OrderStatus.CANCELED, u2);
		Order o3 = new Order(null, Instant.parse("2022-03-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
//		Passa os objetos para serem salvos na tabela do banco, com uma lista
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRespository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		Payment py1 = new Payment(null,Instant.parse("2022-04-20T21:53:07Z"),o1);
//		Para salvar um objeto dependendo de uma relação 1 para 1, devemos chamar a classe "principal" a que não depende
		o1.setPayment(py1);
		orderRepository.save(o1);
		
	}
	
}
