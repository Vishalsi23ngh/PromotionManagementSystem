package com.example.Promotion.Management.System.Service;

import com.example.Promotion.Management.System.Enums.UserType;
import com.example.Promotion.Management.System.Exceptions.InvalidUserTypeException;
import com.example.Promotion.Management.System.Repository.ProductRepository;
import com.example.Promotion.Management.System.Repository.PromotionRepository;
import com.example.Promotion.Management.System.Repository.UserRepository;
import com.example.Promotion.Management.System.Transformer.PromotionTransformer;
import com.example.Promotion.Management.System.dto.requestDto.PromotionRequest;
import com.example.Promotion.Management.System.dto.responseDto.PromotionsResponse;
import com.example.Promotion.Management.System.model.Product;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private  final PromotionRepository promotionRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public PromotionsResponse addPromotion(PromotionRequest promotionRequest) {

        Optional<User> optionalUser = userRepository.findById(promotionRequest.getUserId());
        User user = optionalUser.get();

        Optional<Product> optionalProduct = productRepository.findById(promotionRequest.getProductId());
        Product product = optionalProduct.get();

        UserType userType = user.getUserType();
        if(userType .equals(UserType.BUSINESS_USER)){
            Promotions promotions = PromotionTransformer.promotionsRequestToPromotion(promotionRequest , product ,user);
            Promotions savedPromotion = promotionRepository.save(promotions );
            return  PromotionTransformer.promotionToPromotionResponse(savedPromotion);
        }else{
            throw new InvalidUserTypeException("Promotions can be added by only BUSINESS_USER");
        }

    }


//
//    public PromotionsResponse updatePromotion(PromotionRequest promotionRequest, int promotionId) {
//    }
//
//    public PromotionsResponse getAnalytics(int productId) {
//    }
}
