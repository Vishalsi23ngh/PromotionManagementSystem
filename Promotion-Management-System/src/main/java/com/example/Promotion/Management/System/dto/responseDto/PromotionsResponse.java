package com.example.Promotion.Management.System.dto.responseDto;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.Enums.Promotion_Type;
import com.example.Promotion.Management.System.model.Product;
import com.example.Promotion.Management.System.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PromotionsResponse{

    Integer promotionId;

    Integer productId;

    Integer userId;

    String description;

    Date start_date;

    Date end_date;

    Promotion_Type promotion_type;

    ProductType productType;

    double rating;

    int likes;
}
