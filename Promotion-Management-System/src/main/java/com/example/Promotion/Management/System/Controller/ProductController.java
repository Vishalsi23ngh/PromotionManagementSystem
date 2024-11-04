package com.example.Promotion.Management.System.Controller;

import com.example.Promotion.Management.System.Service.ProductService;
import com.example.Promotion.Management.System.dto.requestDto.ProductRequest;
import com.example.Promotion.Management.System.dto.responseDto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private  final ProductService productService;

    @PostMapping("/add")
    @Operation(summary = "adding product and only business user can add the product")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        ProductResponse response =   productService.addProduct(productRequest);
        return new ResponseEntity<ProductResponse>(response , HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    @Operation(summary = "we can update product description using this api and only business user can update the product details")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest ,@RequestParam int productId){
        ProductResponse response =   productService.updateProduct(productRequest, productId);
        return new ResponseEntity<ProductResponse>(response , HttpStatus.CREATED);
    }

    @PostMapping("/addLikes")
    @Operation(summary ="add likes to product to " , description = "require product id")
    public String  addLikes(@RequestParam Integer productId){
        String response = productService.addLikes(productId);
        return response;
    }

    @PostMapping("/addClicks")
    @Operation(summary = "adding click when everytime we click on product",description = "we need to pass the product id to this api")
    public  String addClicks(@RequestParam Integer productId){
        String ans = productService.addClicks(productId);
        return ans;
    }

//    public Product  addComments(@PathVariable int productId){
//        return  productService.addComments(productId);
//    }

}
