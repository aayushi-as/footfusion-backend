package com.project.footfusionbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String price;
    private String material;
    private Integer rating;
    private String warranty;
    private Long brandIdDto;
    private Long colorIdDto;
    private Long tagIdDto;
    private Long categoryIdDto;
}
