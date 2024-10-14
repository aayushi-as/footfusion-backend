package com.project.footfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.footfusionbackend.dto.OrderRequestDto;
import com.project.footfusionbackend.dto.OrderResponseDTO;
import com.project.footfusionbackend.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("{userId}/place-order")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestDto orderDetails, @PathVariable Long userId) {
        orderService.addOrderDetails(orderDetails.getOrderAddressId(), userId);
        return ResponseEntity.ok("Order placed successfully");
    }
    
    @GetMapping("{userId}/get-order-history")
    public ResponseEntity<?> getAllOrdersByUserId(@PathVariable Long userId) {
        List<OrderResponseDTO> orderInfo = orderService.getAllOrdersByUserId(userId);
        return new ResponseEntity<>(orderInfo, HttpStatus.OK);
    }

    @GetMapping("{userId}/orders/{oid}/get")
    public ResponseEntity<?> getAllOrdersByUserIdAndOrderId(@PathVariable Long userId, @PathVariable Long oid) {
        OrderResponseDTO orderInfo = orderService.getOrderById(userId, oid);
        return new ResponseEntity<>(orderInfo, HttpStatus.OK);
    }
    
    
}
