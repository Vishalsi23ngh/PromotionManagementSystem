package com.example.Promotion.Management.System.Controller;

import com.example.Promotion.Management.System.Service.ProductService;
import com.example.Promotion.Management.System.Service.UserService;
import com.example.Promotion.Management.System.dto.requestDto.UserRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ProductService productService;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){

        // You need to verify the user who is trying to create promotion is a business user or not
        // Promotion model
        UserResponse response = userService.addUser(userRequest);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

     //we can update our user data using this endpoint
    @PatchMapping("/update")
    public  ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest){
        UserResponse response = userService.updateUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
//
//    // Use this endpoint for adding item to purchase history
//    @PostMapping("/addPurchaseHistory")
//    public  String addPurchasedItemToHistory(@RequestParam int userId, @RequestParam int productId){
//        HashMap<Integer ,List<Integer>> addProduct = userService.addPurchasedItemToHistory(userId, productId);
//        return "item Added Successfully";
//    }
//    // we can get all the user purchase history of past using this endpoint
//    @GetMapping("/getHistory")
//    public ResponseEntity<UserHistory> getPurchaseHistory(@RequestParam int userId){
//        UserHistory history = userService.getPurchaseHistory(userId);
//        return new ResponseEntity<>(history, HttpStatus.FOUND);
//    }
//
//    @GetMapping("/promotionByCategory")
//    public ResponseEntity<Promotions> getPromotionByCategory(@RequestParam Promotion_Type promotion_type){
//        Promotions promotion = userService.getPromotionByCategory(promotion_type);
//        return new ResponseEntity<>(promotion,HttpStatus.FOUND);
//    }
//
//    // when the user logs in the popular promotion or the most like promotion he will see on the first page
//    @GetMapping("/promotion")
//    public ResponseEntity<Promotions> getPromotionByPopularity(){
//        Promotions promotion = userService.getPromotionByPopularity();
//        return new ResponseEntity<>(promotion,HttpStatus.FOUND);
//    }
//


}
