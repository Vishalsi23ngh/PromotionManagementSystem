package com.example.Promotion.Management.System.Service;

import com.example.Promotion.Management.System.Enums.Promotion_Type;
import com.example.Promotion.Management.System.Repository.ProductRepository;
import com.example.Promotion.Management.System.Repository.PromotionRepository;
import com.example.Promotion.Management.System.Repository.UserHistoryRepository;
import com.example.Promotion.Management.System.Repository.UserRepository;
import com.example.Promotion.Management.System.Transformer.UserTransformer;
import com.example.Promotion.Management.System.dto.requestDto.UserRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserResponse;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.User;
import com.example.Promotion.Management.System.model.UserHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;

    private  final UserHistoryRepository userHistoryRepository;
    private  final ProductRepository productRepository;

    private  final PromotionRepository promotionRepository;

    private final EmailService emailService;
    public  UserResponse  addUser(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmailId(userRequest.getEmailId());
        if(optionalUser.isPresent()){
            throw new RuntimeException("this email id is already registered , use different emailId");
        }
        User user = UserTransformer.userRequestToUser(userRequest);
        User savedUser = userRepository.save(user);

        String email = userRequest.getEmailId();
        String subject = "Welcome to Our Application";
        String text = "Dear " + userRequest.getName() + ",\n\nThank you for registering with us.";
        emailService.sendEmail(email, subject, text);

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

    public UserResponse getUser(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()){
            return UserTransformer.userToUserResponse(optionalUser.get());
        }else{
            throw new RuntimeException("user is not exist with this id");
        }
    }





    public List<UserHistory> getPurchaseHistory(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            List<UserHistory> userHistory = userHistoryRepository.findByUser_UserIdAndIsPurchasedTrue(userId);
            return  userHistory;
        }else{
            throw  new RuntimeException("no user exist with this id");
        }
    }

    public List<Promotions> getPromotionByCategory(Promotion_Type promotion_type) {
        return promotionRepository.findByProductType(promotion_type);

    }

    public Promotions getPromotionByPopularity() {
        return  null;
    }
}

