package com.example.Promotion.Management.System.Repository;

import com.example.Promotion.Management.System.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory,Integer> {
    UserHistory save(UserHistory history);


    List<UserHistory> findByUserUserId(Integer userId);
}

