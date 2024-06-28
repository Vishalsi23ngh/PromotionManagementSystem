package com.example.Promotion.Management.System.Controller;

import com.example.Promotion.Management.System.Service.ProductService;
import com.example.Promotion.Management.System.dto.requestDto.ProductRequest;
import com.example.Promotion.Management.System.dto.responseDto.ProductResponse;
import com.example.Promotion.Management.System.model.Product;
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
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        ProductResponse response =   productService.addProduct(productRequest);
        return new ResponseEntity<ProductResponse>(response , HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest){
        ProductResponse response =   productService.addProduct(productRequest);
        return new ResponseEntity<ProductResponse>(response , HttpStatus.CREATED);
    }
//
//    @PutMapping
//    public Product  addLikes(@PathVariable int productId){
//        return  productService.addLikes(productId);
//    }
//
//    public Product  addComments(@PathVariable int productId){
//        return  productService.addComments(productId);
//    }

}
