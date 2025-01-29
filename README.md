# Stripe Payment Integration Boilerplate

A Spring Boot application that implements Stripe payment gateway integration with a RESTful API architecture.

## Features

- RESTful API endpoint for payment checkout
- Stripe session creation and management
- Product checkout handling with customizable parameters
- Clean DTO structure for request/response handling

## API Endpoints

### Checkout Endpoint
`POST /product/v1/checkout`

Request body:
```json
{
    "productName": "Example Product",
    "qty": 1,
    "price": 1000,
    "currency": "USD"
}```

Response status:
```json
{
    "sessionId": "stripe_session_id",
    "sessionUrl": "stripe_checkout_url",
    "status": "success/failure",
    "message": "Transaction details"
}

## Project Structure
- StripeCheckoutController: Handles incoming HTTP requests for payment processing

- Request: DTO for handling product checkout requests

- Response: DTO for Stripe session response data

- StripeServiceLayer: Service layer for Stripe integration

### Docs for reference
https://dashboard.stripe.com/
