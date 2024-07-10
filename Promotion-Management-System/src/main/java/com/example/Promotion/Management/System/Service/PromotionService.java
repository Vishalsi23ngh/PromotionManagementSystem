package com.example.Promotion.Management.System.Service;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.Enums.UserType;
import com.example.Promotion.Management.System.Exceptions.InvalidUserTypeException;
import com.example.Promotion.Management.System.Repository.ProductRepository;
import com.example.Promotion.Management.System.Repository.PromotionRepository;
import com.example.Promotion.Management.System.Repository.UserHistoryRepository;
import com.example.Promotion.Management.System.Repository.UserRepository;
import com.example.Promotion.Management.System.Transformer.PromotionTransformer;
import com.example.Promotion.Management.System.dto.requestDto.PromotionRequest;
import com.example.Promotion.Management.System.dto.responseDto.PromotionsResponse;
import com.example.Promotion.Management.System.model.Product;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.User;
import com.example.Promotion.Management.System.model.UserHistory;
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

    private final UserHistoryRepository userHistoryRepository;

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
            promotions.setClicks(click + 1);
            promotionRepository.save(promotions);

            return ResponseEntity.ok("clicks updated successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("promotion not found");
        }

    }

    public ResponseEntity<String> addLikes(Integer promotionId ,Integer userId) {

        Optional<Promotions> optionalPromotions = promotionRepository.findById(promotionId);

        User user = userRepository.findById(userId).get();
        boolean liked = user.isLiked();
        if(optionalPromotions.isPresent()){
            Promotions promotions = optionalPromotions.get();
            if(liked == false){
                Integer likes = promotions.getLikes();
                promotions.setLikes(likes + 1);
                user.setLiked(true);
                promotionRepository.save(promotions);
            }else{
                Integer likes = promotions.getLikes();
                promotions.setLikes(likes - 1);
                user.setLiked(false);
                promotionRepository.save(promotions);
            }

            userRepository.save(user);

            return ResponseEntity.ok("likes updated successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("promotion not found");
        }
    }

    public List<PromotionsResponse> popularPromotion() {
        List<Promotions> promotions = promotionRepository.findPromotionByLike();
        return promotions.stream()
                .map(PromotionTransformer::promotionToPromotionResponse)
                .collect(Collectors.toList());

//          the explaination of the return  is written below
//        Start with a list of promotions.
//        Convert the list to a stream for processing.
//        Sort the promotions in descending order by their like count.
//        Transform each promotion into a response DTO.
//        Collect the transformed items into a new list.

    }

    public List<Promotions> getPromotionByCategory(ProductType productType) {
        return  promotionRepository.findByProductType(productType);
    }

    public List<Promotions> getPromotionsBasedOnUserHistory(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Fetch the last 10 search histories
        List<UserHistory> last10Searches = userHistoryRepository.findTop10ByUserOrderByInteractionTimeDesc(user);

        // Extract product types from the last 10 searches
        List<ProductType> productTypes = last10Searches.stream()
                .map(UserHistory::getProductType)
                .distinct()
                .collect(Collectors.toList());

        // Fetch promotions matching these product types
        return promotionRepository.findByProductTypeIn(productTypes);
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
