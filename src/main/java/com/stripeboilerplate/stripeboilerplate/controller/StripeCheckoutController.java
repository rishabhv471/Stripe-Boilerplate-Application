package com.stripeboilerplate.stripeboilerplate.controller;

import com.stripeboilerplate.stripeboilerplate.dto.Request;
import com.stripeboilerplate.stripeboilerplate.dto.Response;
import com.stripeboilerplate.stripeboilerplate.services.StripeServiceLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/v1")
public class StripeCheckoutController {

    private final StripeServiceLayer stripeServiceLayer;

    @Autowired
    public StripeCheckoutController(StripeServiceLayer stripeServiceLayer) {
        this.stripeServiceLayer = stripeServiceLayer;
    }


    @PostMapping("/checkout")
    public ResponseEntity<Response> checkoutProducts(@RequestBody Request productRequest) {
        Response stripeResponse = stripeServiceLayer.checkout(productRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }
}

