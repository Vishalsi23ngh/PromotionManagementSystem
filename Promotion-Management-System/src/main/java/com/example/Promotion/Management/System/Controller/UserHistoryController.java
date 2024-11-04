package com.example.Promotion.Management.System.Controller;

import com.example.Promotion.Management.System.Service.UserHistoryService;
import com.example.Promotion.Management.System.dto.requestDto.UserHistoryRequest;
import com.example.Promotion.Management.System.dto.responseDto.UserHistoryResponse;
import com.example.Promotion.Management.System.model.UserHistory;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "adding  the user history using this api ", description = " we require the userId and productId and using this id we get the user and product and set the product to user ")
    public ResponseEntity<String> addUserSearchHistory(@RequestBody UserHistoryRequest userHistoryRequest){
        String response = userHistoryService.addUSerHistory(userHistoryRequest);
        return new ResponseEntity<>(response ,HttpStatus.CREATED );
    }

    @GetMapping("/getHistory")
    @Operation(summary = "getting the user History using this end-point ", description = "require user id to fetch the history for  the particular user")
    public ResponseEntity<List<UserHistory>> getUserHistory(@RequestParam Integer userId){
        List<UserHistory> response = userHistoryService.getPromotionBasedOnPastHistory(userId);
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
