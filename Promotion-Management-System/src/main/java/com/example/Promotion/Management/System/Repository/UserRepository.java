package com.example.Promotion.Management.System.Repository;

import com.example.Promotion.Management.System.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User>  findByEmailId(String emailId);

    Optional<User> findById(Integer userId);
}
