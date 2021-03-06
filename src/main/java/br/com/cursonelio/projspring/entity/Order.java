package br.com.cursonelio.projspring.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cursonelio.projspring.entity.enums.OrderStatus;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	Formantando a exibição do Json da data e setando o timezone para o de grenwich/padrão mundial
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

//	Enum
	private Integer orderStatus;
	
	@JsonIgnore
//	Associando esse atributo para a classe User, e passando a relação de muitos pedidos para um usuario
	@ManyToOne
//	Passando a coluna chave estrangeira na tabela User e o nome;
	@JoinColumn(name = "client_id")
	private User client;
	
//	Mapeando relacionamento com a tabela OrderItem, e pegando o order que está na classe OrderItemPK (chave composta) 
//	O obj Order vai utilizar o order dentro da chave composta para mapear os seus itens da classe OrderItem
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
//	1 para 1 está senndo mapeado as 2 entidades para terem o mesmo id, ex: pagamento terá o id do pedido que foi pago;
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {}
	

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus) ;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
//		Validação para não criar sem passar essa informação
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x: items) {
			sum += x.getSubTotal();
		}
		return sum;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
