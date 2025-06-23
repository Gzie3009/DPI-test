package com.gzie.dpi.Controllers;


import com.gzie.dpi.DTOs.OrderDTO;
import com.gzie.dpi.Entities.OrderEntity;
import com.gzie.dpi.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid  @RequestBody OrderDTO orderDTO) {
        OrderEntity orderEntity = orderService.createOrder(orderDTO);
        OrderDTO order= new OrderDTO();
        order.setCustomerId(orderEntity.getCustomer().getId());
        order.setProductName(orderEntity.getProductName());
        order.setId(orderEntity.getId());
        return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
    }

    @PostMapping("/mq")
    public ResponseEntity<OrderDTO> createOrderViaMq(@Valid  @RequestBody OrderDTO orderDTO) {
        orderService.sendOrder(orderDTO);
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.CREATED);
    }
}
