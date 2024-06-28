package com.example.Promotion.Management.System.model;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.Enums.Promotion_Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    User user;

    String name;

    String description;

    @Enumerated(EnumType.STRING)
    ProductType productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Promotions> promotions;

    int noOfLikes;

    String comments;

    int noOfClicks;

    int noOfPurchasedProduct;


}
