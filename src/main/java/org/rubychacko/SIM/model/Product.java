package org.rubychacko.SIM.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private Double price;
}
