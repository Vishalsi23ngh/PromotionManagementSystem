package com.example.Promotion.Management.System.Controller;

import com.example.Promotion.Management.System.Service.PromotionService;
import com.example.Promotion.Management.System.dto.requestDto.PromotionRequest;
import com.example.Promotion.Management.System.dto.responseDto.PromotionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/promotion")
@RequiredArgsConstructor
public class PromotionController {

    private  final PromotionService promotionService;

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
//    @GetMapping("/analytics")
//    public  ResponseEntity<PromotionsResponse> Analytics(@RequestParam int promotionId) {
//        PromotionsResponse response = promotionService.getAnalytics(promotionId);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//
//    }
}
