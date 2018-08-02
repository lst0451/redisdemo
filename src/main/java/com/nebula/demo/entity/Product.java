package com.nebula.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.naming.Name;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product_test")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

//    private BigDecimal price;

    private String description;

}
