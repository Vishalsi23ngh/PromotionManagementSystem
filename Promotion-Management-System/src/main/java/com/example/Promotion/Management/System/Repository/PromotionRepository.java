package com.example.Promotion.Management.System.Repository;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotions, Integer> {
    List<Promotions> findByProductType(ProductType productType);
}

