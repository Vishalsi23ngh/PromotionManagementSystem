package com.example.Promotion.Management.System.model;

import com.example.Promotion.Management.System.Enums.Promotion_Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Promotions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer promotionId;


    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    @JsonIgnore
    Product product;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    User user;

    String description;

    Date start_date;

    Date end_date;

    @Enumerated(EnumType.STRING)
    Promotion_Type promotion_type;

    String ProductType;

    Double rating;

    Integer likes;

}

