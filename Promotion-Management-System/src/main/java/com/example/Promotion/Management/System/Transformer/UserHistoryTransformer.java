package com.example.Promotion.Management.System.Transformer;

import com.example.Promotion.Management.System.dto.requestDto.UserHistoryRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserHistoryResponse;
import com.example.Promotion.Management.System.model.Product;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.User;
import com.example.Promotion.Management.System.model.UserHistory;

import java.time.LocalDateTime;

public class UserHistoryTransformer {

    public static UserHistory userHistoryRequestToUserHistory( User user, Product product){
        return UserHistory.builder()
                .user(user)
                .product(product)
                .productType(product.getProductType())
                .interactionTime(LocalDateTime.now())
                .build();
    }

    public static UserHistoryResponse userHistoryToUserHistoryResponse(Promotions promotions){
        return UserHistoryResponse.builder()
                .productType(promotions.getProduct().getProductType())
                .description(promotions.getDescription())
                .build();
    }
}
