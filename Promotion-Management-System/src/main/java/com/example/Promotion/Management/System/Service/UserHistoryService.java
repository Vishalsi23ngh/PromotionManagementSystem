package com.example.Promotion.Management.System.Service;

import com.example.Promotion.Management.System.Exceptions.UserNotExistException;
import com.example.Promotion.Management.System.Repository.PromotionRepository;
import com.example.Promotion.Management.System.Repository.UserHistoryRepository;
import com.example.Promotion.Management.System.Repository.UserRepository;
import com.example.Promotion.Management.System.Transformer.UserHistoryTransformer;
import com.example.Promotion.Management.System.dto.requestDto.UserHistoryRequest;
import com.example.Promotion.Management.System.model.Promotions;
import com.example.Promotion.Management.System.model.User;
import com.example.Promotion.Management.System.model.UserHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserHistoryService {

    private  final UserHistoryRepository userHistoryRepository;

    private  final UserRepository userRepository;

    private  final PromotionRepository promotionRepository;


    public void addUSerHistory(UserHistoryRequest userHistoryRequest) {

        Optional<User> optionalUser = userRepository.findById(userHistoryRequest.getUserId());
        Optional<Promotions> optionalPromotions = promotionRepository.findById(userHistoryRequest.getPromotionId());


        if (!optionalUser.isPresent()) {
            throw new UserNotExistException("user with this id does not Exist");
        }
        User user = optionalUser.get();

        if (!optionalPromotions.isPresent()) {
            throw new RuntimeException("Promotion is not found with this id" + userHistoryRequest.getPromotionId());
        }
        Promotions promotions = optionalPromotions.get();

        UserHistory history = UserHistoryTransformer.userHistoryRequestToUserHistory(userHistoryRequest, user, promotions);
        userHistoryRepository.save(history);
    }





    public List<UserHistory> getPromotionBasedOnPastHistory(Integer userId) {
        return userHistoryRepository.findByUserUserId(userId);
    }




}
