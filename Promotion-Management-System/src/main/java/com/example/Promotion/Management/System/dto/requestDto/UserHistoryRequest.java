package com.example.Promotion.Management.System.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserHistoryRequest {

   Integer userId;

   Integer promotionId;
}
