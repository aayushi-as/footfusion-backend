package com.project.footfusionbackend.dto;


import com.project.footfusionbackend.model.OrderInfo;
import com.project.footfusionbackend.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private OrderInfo orderInfo;
    private List<OrderItem> orderItem;
}
