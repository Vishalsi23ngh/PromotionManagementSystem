package com.example.Promotion.Management.System.Transformer;

import com.example.Promotion.Management.System.dto.requestDto.UserHistoryRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserHistoryResponse;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.User;
import com.example.Promotion.Management.System.model.UserHistory;

public class UserHistoryTransformer {

    public static UserHistory userHistoryRequestToUserHistory(UserHistoryRequest userHistoryRequest , User user, Promotions promotions){
        return UserHistory.builder()
                .user(user)
                .promotions(promotions)
                .build();
    }

    public static UserHistoryResponse userHistoryToUserHistoryResponse(Promotions promotions){
        return UserHistoryResponse.builder()
                .productType(promotions.getProduct().getProductType())
                .description(promotions.getDescription())
                .build();
    }
}
