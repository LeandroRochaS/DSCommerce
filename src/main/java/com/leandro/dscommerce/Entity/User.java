package com.leandro.dscommerce.Entity;

import java.time.LocalDate;
import java.util.ArrayList;

import com.leandro.dscommerce.Entity.Order.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private LocalDate birthDate;

    private String password;

    private String[] roles;

    @OneToMany(mappedBy = "client")
    private ArrayList<Order> orders = new ArrayList<>();


    // Gets and Setters
}
