package com.project.footfusionbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="discountId", nullable=false)
    @JsonIgnore
    private Discount discount;

    private String name;

    private String price;

    @ManyToOne
    @JoinColumn(name="colorId", nullable=false)
    @JsonIgnore
    private Color color;
    private String material;

    @ManyToOne
    @JoinColumn(name = "tagId", nullable = false)
    @JsonIgnore
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="categoryId", nullable=false)
    @JsonIgnore
    private Category category;

    private Integer rating;

    private String warranty;

    private LocalDateTime createdAt;

}
