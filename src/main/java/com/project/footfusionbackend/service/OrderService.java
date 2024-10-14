package com.project.footfusionbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.footfusionbackend.dto.OrderResponseDTO;
import com.project.footfusionbackend.model.Address;
import com.project.footfusionbackend.model.Cart;
import com.project.footfusionbackend.model.OrderInfo;
import com.project.footfusionbackend.model.OrderItem;
import com.project.footfusionbackend.repository.OrderInfoRepository;
import com.project.footfusionbackend.repository.OrderItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Service
public class OrderService {
    
    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired 
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    public void addOrderDetails(Long addressId, Long userId) {
        Address address = addressService.getAddressByAddressId(userId, addressId);
        Integer cartPrice = cartService.getCartTotal(userId);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderDate(LocalDate.now().toString());
        orderInfo.setPrice(cartPrice);
        orderInfo.setOrderBlockNo(address.getBlockNo());
        orderInfo.setOrderDescription(address.getDescription());
        orderInfo.setOrderCity(address.getCity());
        orderInfo.setOrderState(address.getState());
        orderInfo.setOrderCountry(address.getCountry());
        orderInfo.setOrderZipcode(address.getZipcode());
        orderInfo.setContactNo(address.getContactNo());
        orderInfo.setUser(address.getUser());


        OrderInfo orderInfoEntered = orderInfoRepository.save(orderInfo);

        List<Cart> productList = cartService.getAllProductsFromCart(userId);

        for (Cart cartProduct : productList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartProduct.getQuantity());
            orderItem.setSize(cartProduct.getSize());
            orderItem.setPrice(cartProduct.getProduct().getPrice());
            orderItem.setProduct(cartProduct.getProduct());
            orderItem.setOrder(orderInfoEntered);

            orderItemRepository.save(orderItem);
            cartService.removeProductFromCart(cartProduct);
        }
    }

    public List<OrderResponseDTO> getAllOrdersByUserId(Long userId) {
        List<OrderInfo> orderInfoList = orderInfoRepository.findByUserUserId(userId);
        List<OrderResponseDTO> orderResponseList = new ArrayList<>();

        for (OrderInfo orderInfo : orderInfoList) {
            List<OrderItem> orderItemList = orderItemRepository.findByOrderOrderId(orderInfo.getOrderId());
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO(orderInfo, orderItemList);
            orderResponseList.add(orderResponseDTO);
        }

        return orderResponseList;
    }

    public OrderResponseDTO getOrderById(Long userId, Long oid) {
        OrderInfo orderInfo = orderInfoRepository.findByOrderId(oid);
        List<OrderItem> orderItemList = orderItemRepository.findByOrderOrderId(orderInfo.getOrderId());
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO(orderInfo, orderItemList);
        return orderResponseDTO;
    }
}
