package com.example.Promotion.Management.System.dto.requestDto;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.Enums.Promotion_Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PromotionRequest {

    Integer userId;

    Integer productId;

    String description;

    Date start_date;

    Date end_date;

    Promotion_Type promotion_type;

    ProductType productType;

}
