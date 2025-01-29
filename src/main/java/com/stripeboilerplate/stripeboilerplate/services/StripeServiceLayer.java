package com.stripeboilerplate.stripeboilerplate.services;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripeboilerplate.stripeboilerplate.dto.Request;
import com.stripeboilerplate.stripeboilerplate.dto.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceLayer {

    @Value("${stripe.secretKey}")
    private String secretKey;

    public Response checkout(Request req){
        Stripe.apiKey = secretKey;
        SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                .builder()
                .setName(req.getProductName())
                .build();

        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                        .builder()
                        .setCurrency("usd")
                        .setUnitAmount(req.getPrice()*100)
                        .setProductData(productData)
                        .build();

        SessionCreateParams.LineItem lineItem= SessionCreateParams.LineItem
                .builder()
                .setQuantity(req.getQty())
                .setPriceData(priceData)
                .build();

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/failed")
                .addLineItem(lineItem)
                .build();

        Session session = null;
        try {
            session = Session.create(params);
        } catch (StripeException e) {
            return Response.builder()
                    .status("Error")
                    .message("Failed to create payment session: " + e.getMessage())
                    .build();
        }

        if (session == null) {
            return Response.builder()
                    .status("Error")
                    .message("Session creation failed")
                    .build();
        }

        return Response.builder()
                .status("Success")
                .message("Payment session created")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();


}}
