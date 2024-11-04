package com.example.Promotion.Management.System.Transformer;

import com.example.Promotion.Management.System.dto.requestDto.UserRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserResponse;
import com.example.Promotion.Management.System.model.User;

public class UserTransformer {

    public  static User userRequestToUser(UserRequest userRequest){

        return  User.builder()
                .name(userRequest.getName())
                .age((userRequest.getAge()))
                .address(userRequest.getAddress())
                .gender((userRequest.getGender()))
                .userType(userRequest.getUserType())
                .profession(userRequest.getProfession())
                .emailId(userRequest.getEmailId())
                .password(userRequest.getPassword())
                .build();
    }

    public  static UserResponse userToUserResponse(User user){
        return UserResponse.builder()
                .name(user.getName())
                .address(user.getAddress())
                .gender(user.getGender())
                .age(user.getAge())
                .profession(user.getProfession())
                .userType(user.getUserType())
                .emailId(user.getEmailId())
                .build();
    }
}
