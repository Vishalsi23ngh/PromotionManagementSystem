package com.example.Promotion.Management.System.model;

import com.example.Promotion.Management.System.Enums.ProductType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotionId", nullable = false)
    Promotions promotions;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private LocalDateTime interactionTime;


}
