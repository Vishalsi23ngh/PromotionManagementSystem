package com.example.Promotion.Management.System.dto.responseDto;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.Enums.Promotion_Type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponse {
    Integer productId;

    String name;

    String description;

    ProductType productType;

    int noOfLikes;

    String comments;

    int noOfClicks;

    int noOfPurchasedProduct;
}
