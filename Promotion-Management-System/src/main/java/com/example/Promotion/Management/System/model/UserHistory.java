package com.example.Promotion.Management.System.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    User user;
    @ManyToOne
    @JoinColumn(name = "promotionId", nullable = false)
    Promotions promotions;


}
