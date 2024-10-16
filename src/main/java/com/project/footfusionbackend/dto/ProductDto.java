package com.project.footfusionbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.project.footfusionbackend.model.Tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank(message = "Product name should not be blank")
    private String name;

    @NotNull(message = "Price should not be null")
    private Integer price;
    private String material;
    private Double rating;
    private String warranty;

    @NotNull(message = "Brand id should not be null")
    private Long brandIdDto;

    @NotNull(message = "Tag should not be null")
    private Tag tag;

    @NotNull(message = "Category id should not be null")
    private Long categoryIdDto;
}
