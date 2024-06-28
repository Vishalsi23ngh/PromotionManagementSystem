package com.example.Promotion.Management.System.dto.responseDto;

import com.example.Promotion.Management.System.Enums.Gender;
import com.example.Promotion.Management.System.Enums.Profession;
import com.example.Promotion.Management.System.Enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String name;

    int userId;

    String emailId;

    String password;

    String address;

    int age;

    Gender gender;

    Profession profession;

    UserType userType;
}
