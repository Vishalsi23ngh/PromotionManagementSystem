package com.example.Promotion.Management.System.Controller;

import com.example.Promotion.Management.System.Service.UserHistoryService;
import com.example.Promotion.Management.System.dto.requestDto.UserHistoryRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserHistoryResponse;
import com.example.Promotion.Management.System.model.UserHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/history")
public class UserHistoryController {

    private final UserHistoryService userHistoryService;

    @PostMapping("/addHistory")
    public ResponseEntity<Void> addUserSearchHistory(@RequestBody UserHistoryRequest userHistoryRequest){
        userHistoryService.addUSerHistory(userHistoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getHistory")
    public ResponseEntity<List<UserHistory>> getUserHistory(@RequestParam Integer userId){
        List<UserHistory> response = userHistoryService.getPromotionBasedOnPastHistory(userId);
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
