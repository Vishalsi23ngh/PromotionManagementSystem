package com.example.Promotion.Management.System.dto.requestDto;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.Enums.Promotion_Type;
import com.example.Promotion.Management.System.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequest {

    Integer userId;

    String name;

    String description;

    ProductType productType;


}
