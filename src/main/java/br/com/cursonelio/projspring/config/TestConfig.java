package br.com.cursonelio.projspring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cursonelio.projspring.entity.Order;
import br.com.cursonelio.projspring.entity.User;
import br.com.cursonelio.projspring.entity.enums.OrderStatus;
import br.com.cursonelio.projspring.repository.OrderRepository;
import br.com.cursonelio.projspring.repository.UserRepository;

@Configuration
@Profile("test")
// Implementa uma interface com um metodo que roda ao iniciar o projeto
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
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
	}
	
}
