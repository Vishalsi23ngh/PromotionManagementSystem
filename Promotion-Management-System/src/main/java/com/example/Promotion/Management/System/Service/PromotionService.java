package com.example.Promotion.Management.System.Service;

import com.example.Promotion.Management.System.Enums.ProductType;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ResponseEntity addClicks(Integer promotionId) {

        Optional<Promotions> optionalPromotions = promotionRepository.findById(promotionId);

        if(optionalPromotions.isPresent()){
            Promotions promotions = optionalPromotions.get();

            Integer click = promotions.getClicks();
            promotions.setClicks(click + 1) ;
            promotionRepository.save(promotions);

            return ResponseEntity.ok("clicks updated successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("promotion not found");
        }

    }

    public ResponseEntity<String> addLikes(Integer promotionId) {
        Optional<Promotions> optionalPromotions = promotionRepository.findById(promotionId);

        if(optionalPromotions.isPresent()){
            Promotions promotions = optionalPromotions.get();

            Integer likes = promotions.getLikes();
            promotions.setLikes(likes + 1); ;
            promotionRepository.save(promotions);

            return ResponseEntity.ok("likes updated successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("promotion not found");
        }
    }

    public List<PromotionsResponse> popularPromotion() {
        List<Promotions> promotions = promotionRepository.findAll();
        return promotions.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getLikes(), p1.getLikes()))
                .map(PromotionTransformer::promotionToPromotionResponse)
                .collect(Collectors.toList());

//          the explaination of the return  is written below
//        Start with a list of promotions.
//        Convert the list to a stream for processing.
//        Sort the promotions in descending order by their like count.
//        Transform each promotion into a response DTO.
//        Collect the transformed items into a new list.

    }

    public List<PromotionsResponse> getPromotionByCategory(ProductType productType) {

        List<Promotions> promotions = promotionRepository.findByProductType(productType);

        if(promotions.isEmpty()){
            throw  new RuntimeException("No promotion found with  this product type");
        }
        return promotions.stream()
                .map(PromotionTransformer::promotionToPromotionResponse)
                .collect(Collectors.toList());
    }


//    public ResponseEntity<String> addRating(Integer promotionId ,Integer val) {
//        Optional<Promotions> optionalPromotions = promotionRepository.findById(promotionId);
//        if(optionalPromotions.isPresent()){
//            Promotions promotions = optionalPromotions.get();
//
//            promotions.setRating(val);
//        }
//    }


//
//    public PromotionsResponse updatePromotion(PromotionRequest promotionRequest, int promotionId) {
//    }
//
//
}
