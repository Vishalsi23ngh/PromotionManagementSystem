package com.example.Promotion.Management.System.Repository;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotions, Integer> {



    @Query("SELECT p FROM Promotions p WHERE p.productType = :productType")
    List<Promotions> findByProductType(@Param("productType") ProductType productType);

    @Query("SELECT p FROM Promotions p ORDER BY p.likes DESC")
    List<Promotions> findPromotionByLike();


    @Query("SELECT p FROM Promotions p WHERE p.productType IN :productTypes")
    List<Promotions> findByProductTypeIn(List<ProductType> productTypes);

    Optional<Promotions>  findById(Integer promotionId);
}

