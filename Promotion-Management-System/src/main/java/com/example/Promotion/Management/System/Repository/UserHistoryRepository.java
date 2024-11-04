package com.example.Promotion.Management.System.Repository;

import com.example.Promotion.Management.System.model.User;
import com.example.Promotion.Management.System.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory,Integer> {


    List<UserHistory> findByUserUserId(Integer userId);


    @Query("SELECT uh FROM UserHistory uh WHERE uh.user = :user ORDER BY uh.interactionTime DESC")
    List<UserHistory> findTop10ByUserOrderByInteractionTimeDesc(User user);


    List<UserHistory> findByUser_UserIdAndIsPurchasedTrue(Integer userId);

}

