package com.jan14.ecommerce.service;

import com.jan14.ecommerce.dto.Purchase;
import com.jan14.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
