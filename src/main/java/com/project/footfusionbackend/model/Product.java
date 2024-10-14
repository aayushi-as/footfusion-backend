package com.project.footfusionbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name="brandId", nullable=false)
    @JsonIgnore
    private Brand brand;

    private String name;

    private Integer price;

    private String material;

    @Enumerated(EnumType.ORDINAL)
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="categoryId", nullable=false)
    @JsonIgnore
    private Category category;

    private Double rating;

    private String warranty;

    private LocalDateTime createdAt;

}
