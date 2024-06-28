package com.example.Promotion.Management.System.dto.requestDto;

import com.example.Promotion.Management.System.Enums.Gender;
import com.example.Promotion.Management.System.Enums.Profession;
import com.example.Promotion.Management.System.Enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String name;

    String password;

    String emailId;

    String address;

    int age;

    Gender gender;

    Profession profession;

    UserType userType;

}
