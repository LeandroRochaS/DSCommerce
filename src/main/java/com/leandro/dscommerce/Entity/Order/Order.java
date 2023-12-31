package com.leandro.dscommerce.Entity.Order;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.leandro.dscommerce.Entity.Payment;
import com.leandro.dscommerce.Entity.Product;
import com.leandro.dscommerce.Entity.User;

@Entity
@Table(name = "tb_order")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToMany
    @JoinTable(name = "tb_order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "id.order")
    private List<OrderItem> items = new ArrayList<>();
    
    
    public Order() {
    	
    }

    public Order(Long id, Instant moment, OrderStatus status, User client, Payment payment, Set<Product> products,
			List<OrderItem> items) {
		super();
		this.id = id;
		this.moment = moment;
		this.status = status;
		this.client = client;
		this.payment = payment;
		this.products = products;
		this.items = items;
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



	public OrderStatus getStatus() {
		return status;
	}



	public void setStatus(OrderStatus status) {
		this.status = status;
	}



	public User getClient() {
		return client;
	}



	public void setClient(User client) {
		this.client = client;
	}



	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}



	public void setProducts(Set<Product> products) {
		this.products = products;
	}



	public void setItems(List<OrderItem> items) {
		this.items = items;
	}



	public List<OrderItem> getItems() {
        return items;
    }

    public List<Product> getProducts() {
        return items.stream().map(x -> x.getProduct()).toList();
    }

}
