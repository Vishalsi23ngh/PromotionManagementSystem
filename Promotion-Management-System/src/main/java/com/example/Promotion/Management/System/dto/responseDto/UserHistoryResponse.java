package com.example.Promotion.Management.System.dto.responseDto;

import com.example.Promotion.Management.System.Enums.ProductType;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserHistoryResponse {

   String description;

   ProductType productType;
}
