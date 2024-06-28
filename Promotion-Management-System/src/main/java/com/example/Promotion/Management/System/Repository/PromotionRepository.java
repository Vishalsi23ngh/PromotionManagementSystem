package com.example.Promotion.Management.System.Repository;

import com.example.Promotion.Management.System.model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotions, Integer> {
}
