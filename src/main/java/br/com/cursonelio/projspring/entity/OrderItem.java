package br.com.cursonelio.projspring.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cursonelio.projspring.entity.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
//	ID composto, com uma classe auxiliar // Chave primária composta de uma classe auxiliar
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {}
	
// Para criar um item de pedido, é nescessário passar o pedido e o produto. Isso é feito com a classe auxiliar de chave composta que 
//	armazena essas informações para que possamos identificar as linhas da tabela OrderITem ( identificar o item do pedido )
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void SetOrder(Order order) {
		id.setOrder(order);
	}

	public Product getProduct() {
		return id.getProduct();
	}
	
	public void SetProduct(Product product) {
		id.setProduct(product);
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

//	Colocar o "get" na frente do metodo faz o mesmo aparecer no Json;
	public Double getSubTotal() {
		return price * quantity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
