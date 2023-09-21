package com.mac.productservice.service;

import com.mac.productservice.dto.ProductRequest;
import com.mac.productservice.dto.ProductResponse;
import com.mac.productservice.model.Product;
import com.mac.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(
                productRequest.getPrice()).build();
        productRepository.save(product);
       // log.info("Product {} is saved" + product.getId());

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).
                price(product.getPrice()).build();
    }


}
