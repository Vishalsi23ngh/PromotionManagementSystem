package com.example.Promotion.Management.System.Controller;

import com.example.Promotion.Management.System.Enums.Promotion_Type;
import com.example.Promotion.Management.System.Service.EmailService;
import com.example.Promotion.Management.System.Service.ProductService;
import com.example.Promotion.Management.System.Service.UserHistoryService;
import com.example.Promotion.Management.System.Service.UserService;
import com.example.Promotion.Management.System.dto.requestDto.UserRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserResponse;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.UserHistory;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ProductService productService;

    private  final UserHistoryService userHistoryService;


    @PostMapping("/register")
    @Operation(summary = "creating the user with this api")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){

        // You need to verify the user who is trying to create promotion is a business user or not
        // Promotion model
        UserResponse response = userService.addUser(userRequest);

        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

     //we can update our user data using this endpoint
    @PatchMapping("/update")
    @Operation(summary = "updating the user with this endpoint")
    public  ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest){
        UserResponse response = userService.updateUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "getting user information with this endpoint")
    public UserResponse getUser(@RequestParam int userId){
        return userService.getUser(userId);
    }



    // Use this endpoint for adding item to purchase history
    @PostMapping("/addPurchaseHistory")
    @Operation(summary = "adding the user history with this endpoint" , description = "assigning the product to the user using the userId and productId")
    public  String addPurchasedItemToHistory(@RequestParam int userId, @RequestParam int productId){
        return  userHistoryService.addPurchasedItemToHistory(userId, productId);

    }

    // we can get all the user purchase history of past using this endpoint
    @GetMapping("/getHistory")
    @Operation(summary = "we can get all the user purchase history of past using this endpoint")
    public ResponseEntity<List<UserHistory>> getPurchaseHistory(@RequestParam int userId){
        List<UserHistory> history = userService.getPurchaseHistory(userId);
        return new ResponseEntity<>(history, HttpStatus.FOUND);
    }

    @GetMapping("/promotionByCategory")
    @Operation(summary =  "we can find all the promotion by selecting the category of the product like sports product or cosmetics etc")
    public ResponseEntity<List<Promotions>> getPromotionByCategory(@RequestParam Promotion_Type promotion_type){
        List<Promotions> promotion = userService.getPromotionByCategory(promotion_type);
        return new ResponseEntity<>(promotion,HttpStatus.FOUND);
    }

    // when the user logs in the popular promotion or the most like promotion he will see on the first page
    @GetMapping("/promotion")
    @Operation(summary =  "getting all the popular promotion")
    public ResponseEntity<Promotions> getPromotionByPopularity(){
        Promotions promotion = userService.getPromotionByPopularity();
        return new ResponseEntity<>(promotion,HttpStatus.FOUND);
    }



}
