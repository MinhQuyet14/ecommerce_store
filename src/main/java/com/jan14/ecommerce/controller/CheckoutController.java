package com.jan14.ecommerce.controller;

import com.jan14.ecommerce.dto.Purchase;
import com.jan14.ecommerce.dto.PurchaseResponse;
import com.jan14.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

//    public CheckoutController(CheckoutService checkoutService){
//        this.checkoutService = checkoutService;
//    }
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        //PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return checkoutService.placeOrder(purchase);
    }
}
