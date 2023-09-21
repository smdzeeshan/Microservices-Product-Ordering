package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));

        // return ResponseEntity.ok("Order placed successfully");
        // return "Order placed successfully";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CompletableFuture<String> fallBackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        //return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Oops!! Something went wrong. Please try again after sometime.");
        // return "Oops!! Something went wrong. Please try again sometime.";
        return CompletableFuture.supplyAsync(() -> "Oops!! Something went wrong. Please try again sometime.");
    }
}
