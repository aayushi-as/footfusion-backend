package com.project.footfusionbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.project.footfusionbackend.model.Tag;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private Integer price;
    private String material;
    private Double rating;
    private String warranty;
    private Long brandIdDto;
    private Tag tag;
    private Long categoryIdDto;
}
