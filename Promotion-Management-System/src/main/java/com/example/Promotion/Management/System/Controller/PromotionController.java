package com.example.Promotion.Management.System.Controller;

import com.example.Promotion.Management.System.Enums.ProductType;
import com.example.Promotion.Management.System.Service.PromotionService;
import com.example.Promotion.Management.System.Service.UserService;
import com.example.Promotion.Management.System.dto.requestDto.PromotionRequest;
import com.example.Promotion.Management.System.dto.responseDto.PromotionsResponse;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotion")
@RequiredArgsConstructor
public class PromotionController {

    private  final PromotionService promotionService;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<PromotionsResponse> addPromotion(@RequestBody PromotionRequest promotionRequest){
        PromotionsResponse response = promotionService.addPromotion(promotionRequest);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
//
//    @PatchMapping("/update")
//    public  ResponseEntity<PromotionsResponse> updatePromotion(@RequestBody PromotionRequest promotionRequest, @RequestParam int promotionId){
//        PromotionsResponse response = promotionService.updatePromotion(promotionRequest , promotionId);
//        return  new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    // we can calculate the  no of likes and also get the no of product sold , also we can create the conversion rate using (no of product sold/no of clicked *
//    // 100)
    @GetMapping("/popularPromotion")
    public  ResponseEntity<List<PromotionsResponse>> getPopularPromotion() {
        List<PromotionsResponse> response = promotionService.popularPromotion();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/promotionByProductType")
    public  ResponseEntity<List<Promotions>> getPromotionBasedOnProductCategory(@RequestParam ProductType productType){
        List<Promotions> responses = promotionService.getPromotionByCategory(productType);
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<Promotions>> getRecommendedPromotions(@RequestParam Integer userId) {
        List<Promotions> recommendedPromotions = promotionService.getPromotionsBasedOnUserHistory(userId);
        return ResponseEntity.ok(recommendedPromotions);
    }
    @PostMapping("addClicks")
    public ResponseEntity<String> addNoOFClicks(@RequestParam Integer promotionId){
        ResponseEntity<String> response = promotionService.addClicks(promotionId);
        return new ResponseEntity<>(response.getBody() ,response.getStatusCode());
    }

    @PostMapping("addLikes")
    public ResponseEntity<String> addLikes(@RequestParam Integer promotionId ,@RequestParam Integer userId){
        ResponseEntity<String> response = promotionService.addLikes(promotionId ,userId);


        return new ResponseEntity<>(response.getBody() ,response.getStatusCode());
    }



//    public ResponseEntity<String> addRating(@RequestParam Integer promotionId ,@RequestParam Integer val){
//        ResponseEntity<String> response = promotionService.addRating(promotionId ,val);
//        return new ResponseEntity<>(response.getBody() ,response.getStatusCode());
//    }



}
