package com.project.footfusionbackend.dto;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecRequestDTO {
    
    private Optional<Long> brandId;
    private Optional<Long> categoryId;
    private Optional<Integer> tag;
    private Optional<Integer> minPrice;
    private Optional<Integer> maxPrice;
    private int page = 0;
    private int size = 2;
    private String[] sortList;

}
