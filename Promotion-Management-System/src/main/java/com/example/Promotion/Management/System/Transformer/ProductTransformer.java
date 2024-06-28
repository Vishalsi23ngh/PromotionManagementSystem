package com.example.Promotion.Management.System.Transformer;

import com.example.Promotion.Management.System.dto.requestDto.ProductRequest;
import com.example.Promotion.Management.System.dto.responseDto.ProductResponse;
import com.example.Promotion.Management.System.model.Product;
import com.example.Promotion.Management.System.model.User;

public class ProductTransformer {
    public static Product productRequestToProduct(ProductRequest productRequest, User user) {
        return Product.builder()
                .user(user)
                .name(productRequest.getName())
                .productType(productRequest.getProductType())
                .description(productRequest.getDescription())
                .build();
    }

    public static ProductResponse productToProductResponse(Product savedProduct) {

        return ProductResponse.builder()
                .productId(savedProduct.getProductId())
                .name(savedProduct.getName())
                .productType(savedProduct.getProductType())
                .description(savedProduct.getDescription())
                .build();
    }
}
