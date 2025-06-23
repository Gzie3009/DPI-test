package com.gzie.dpi.Services;


import com.gzie.dpi.Config.RabbitMQConfig;
import com.gzie.dpi.DTOs.OrderDTO;
import com.gzie.dpi.Entities.CustomerEntity;
import com.gzie.dpi.Entities.OrderEntity;
import com.gzie.dpi.Repositories.CustomerRepository;
import com.gzie.dpi.Repositories.OrdersRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public OrderEntity createOrder(OrderDTO order) {
        Optional<OrderEntity> orderEntity = ordersRepository.findById(order.getId());
        if(orderEntity.isPresent()) {
            return orderEntity.get();
        }
        OrderEntity newOrder = new OrderEntity();
        Optional<CustomerEntity> customer= customerRepository.findById(order.getCustomerId());
        if(customer.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        newOrder.setCustomer(customer.get());
        newOrder.setProductName(order.getProductName());
        ordersRepository.save(newOrder);
        return newOrder;
    }

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendOrder(OrderDTO order) {
        System.out.println("Order sent from producer");
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_QUEUE, order);
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void receiveOrder(OrderDTO order) {
        System.out.println("Order recieved at consumer");
        createOrder(order);
    }
}
