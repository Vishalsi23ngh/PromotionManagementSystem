package com.example.Promotion.Management.System.Service;

import com.example.Promotion.Management.System.Exceptions.UserNotExistException;
import com.example.Promotion.Management.System.Repository.ProductRepository;
import com.example.Promotion.Management.System.Repository.PromotionRepository;
import com.example.Promotion.Management.System.Repository.UserHistoryRepository;
import com.example.Promotion.Management.System.Repository.UserRepository;
import com.example.Promotion.Management.System.Transformer.UserHistoryTransformer;
import com.example.Promotion.Management.System.dto.requestDto.UserHistoryRequest;
import com.example.Promotion.Management.System.model.Product;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.User;
import com.example.Promotion.Management.System.model.UserHistory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserHistoryService {

    private  final UserHistoryRepository userHistoryRepository;

    private  final UserRepository userRepository;

    private  final PromotionRepository promotionRepository;

    private  final  ProductRepository productRepository;



    public String addUSerHistory(UserHistoryRequest userHistoryRequest) {

        Optional<User> optionalUser = userRepository.findById(userHistoryRequest.getUserId());
        Optional<Product> optionalProduct = productRepository.findById(userHistoryRequest.getProductId());


        if (!optionalUser.isPresent()) {
            throw new UserNotExistException("user with this id does not Exist");
        }
        User user = optionalUser.get();

        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Promotion is not found with this id" + userHistoryRequest.getProductId());
        }
        Product product = optionalProduct.get();

        UserHistory history = UserHistoryTransformer.userHistoryRequestToUserHistory( user, product);
        userHistoryRepository.save(history);

        return "history is added with productId : "+history.getProduct() +" ans userId : "+history.getUser().getUserId();
    }





    public List<UserHistory> getPromotionBasedOnPastHistory(Integer userId) {
        return userHistoryRepository.findByUserUserId(userId);
    }


    public String addPurchasedItemToHistory(int userId, int productId) {
        //checking that user already exist or not
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw  new UserNotExistException("no user exist with the userId: " + userId);
        }
        User user = optionalUser.get();

        // checking for the product is present or not
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("product is not found with this productId : " + productId);
        }

        Product product = optionalProduct.get();

        UserHistory orderHistory = UserHistoryTransformer.userHistoryRequestToUserHistory(user, product);
        orderHistory.setPurchased(true);

        return "History created";

    }
}
