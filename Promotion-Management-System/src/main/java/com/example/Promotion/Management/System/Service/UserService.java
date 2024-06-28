package com.example.Promotion.Management.System.Service;

import com.example.Promotion.Management.System.Repository.UserRepository;
import com.example.Promotion.Management.System.Transformer.UserTransformer;
import com.example.Promotion.Management.System.dto.requestDto.UserRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserResponse;
import com.example.Promotion.Management.System.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;

    public  UserResponse  addUser(UserRequest userRequest) {
        User user = UserTransformer.userRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserTransformer.userToUserResponse(savedUser);
    }



    public UserResponse updateUser(UserRequest userRequest) {
        Optional<User> existingUser = userRepository. findByEmailId(userRequest.getEmailId());

        User user;

        if(existingUser.isPresent()){
            user = existingUser.get();

            user.setName(userRequest.getName());
            user.setAddress(userRequest.getAddress());
            user.setAge(userRequest.getAge());
            user.setProfession(userRequest.getProfession());
        }
        else{
            user = UserTransformer.userRequestToUser(userRequest);
        }
        User savedUser = userRepository.save(user);
        return  UserTransformer.userToUserResponse(savedUser);
    }
//
//    public HashMap<Integer, List<Integer>> addPurchasedItemToHistory(int userId, int productId) {
//    }
//
//    public UserHistory getPurchaseHistory(int userId) {
//    }
//
//    public Promotions getPromotionByCategory(Promotion_Type promotion_type) {
//    }
//
//    public Promotions getPromotionByPopularity() {
//    }
}

