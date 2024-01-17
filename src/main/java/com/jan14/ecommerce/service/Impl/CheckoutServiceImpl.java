package com.jan14.ecommerce.service.Impl;

import com.jan14.ecommerce.dao.CustomerRepository;
import com.jan14.ecommerce.dto.Purchase;
import com.jan14.ecommerce.dto.PurchaseResponse;
import com.jan14.ecommerce.entity.Customer;
import com.jan14.ecommerce.entity.Order;
import com.jan14.ecommerce.entity.OrderItem;
import com.jan14.ecommerce.service.CheckoutService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //retrieve  the order info from dto
        Order order = purchase.getOrder();


        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);


        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);// <=> item -> order.add(item)

        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();

        //check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);
        if(customerFromDB != null){
            customer = customerFromDB;
        }

        customer.add(order);
        //save to db
        customerRepository.save(customer);
        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}
