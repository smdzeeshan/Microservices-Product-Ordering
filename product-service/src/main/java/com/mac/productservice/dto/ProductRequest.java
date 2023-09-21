package com.mac.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;



}
