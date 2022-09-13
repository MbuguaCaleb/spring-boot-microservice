package com.codewithcaleb.productservice.service;

import com.codewithcaleb.productservice.dto.ProductRequest;
import com.codewithcaleb.productservice.dto.ProductResponse;
import com.codewithcaleb.productservice.model.Product;
import com.codewithcaleb.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){


        //creating Object of Type Product
        //instance of product Object
        Product product = Product.builder().name(productRequest.getName())
                                 .description(productRequest.getDescription())
                                 .price(productRequest.getPrice())
                                 .build();

        productRepository.save(product);

        //concat method
        //log.info("Product "+ product.getId()+ "is saved");

        //method Two
        //self4j helps us use plc
        log.info("Product {} is saved",product.getId());


    }

    public List<ProductResponse> getAllProducts() {
       List<Product> products = productRepository.findAll();

       return products.stream().map(this::mapToProductResponse).toList();
       //return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build();
    }
}
