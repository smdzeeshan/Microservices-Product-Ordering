package com.mac.inventoryservice.controller;

import com.mac.inventoryservice.dto.InventoryResponse;
import com.mac.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //localhost:8083/api/inventory/Iphone13,Iphone13_red
    //localhost:8083/api/inventory?skuCode=Iphone13&skuCode=Iphone13_red

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        List<InventoryResponse> inventoryResponses = null;
        try {
            inventoryResponses = inventoryService.isInStock(skuCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventoryResponses;
    }
}
