package com.kh.product.controller;


import com.kh.product.dao.entity.Product;
import com.kh.product.req.ReqSave;
import com.kh.product.req.ReqUpdate;
import com.kh.product.service.ApiResponse;
import com.kh.product.service.ProductSVC;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/api/products")
@Controller
@RequiredArgsConstructor
public class ApiProductController {

  private final ProductSVC productSVC;
  private final MessageSource messageSource;

    //초기화면
  @GetMapping
  public String init(){ return "/api/product/init";}

  //등록
  @ResponseBody
  @PostMapping
  public ApiResponse<Object> add(
      @RequestBody
      @Valid ReqSave reqSave, BindingResult bindingResult){
    log.info("reqSave={}",reqSave);
    ApiResponse<Object> res = null;

    Product product = new Product();
    BeanUtils.copyProperties(reqSave,product);

    Long pid = productSVC.save(product);

    Optional<Product> optionalProduct = productSVC.findById(pid);
    Product saveProduct = optionalProduct.get();
    res = ApiResponse.createApiResponse("00","성공", saveProduct);

    return res;
  }

  //조회
  @ResponseBody
  @GetMapping("/{pid}")
  public ApiResponse<Product> findById(@PathVariable("pid") Long pid){
    ApiResponse<Product> res = null;
    Optional<Product> optionalProduct = productSVC.findById(pid);

    Product findedProduct = null;
    if(optionalProduct.isPresent()){
      findedProduct = optionalProduct.get();
      res = ApiResponse.createApiResponse("00","성공", findedProduct);
    }else{
      res = ApiResponse.createApiResponse("01","실패",null);
    }
    return res;
  }

  //수정
  @ResponseBody
  @PatchMapping("/{pid}")
  public ApiResponse<Object> update(
      @PathVariable Long pid,
      @Valid @RequestBody ReqUpdate reqUpdate,
      BindingResult bindingResult){
    log.info("reqUpdate={}", reqUpdate);
    ApiResponse<Object> res = null;

    Product product = new Product();
    BeanUtils.copyProperties(reqUpdate, product);
    int row = productSVC.updateById(pid, product);

    if(row == 1){
      Product findedProduct = productSVC.findById(pid).get();
      res = ApiResponse.createApiResponse("00","성공",findedProduct);
    }else{
      res = ApiResponse.createApiResponse("01","실패",null);
    }
    return res;
  }

  @ResponseBody
  @DeleteMapping("/{pid}")
  public ApiResponse<String> delete(@PathVariable Long pid){
    ApiResponse<String> res = null;

    int row = productSVC.deleteById(pid);
    if(row == 1){
      res = ApiResponse.createApiResponse("00","성공",null);
    }else{
      res = ApiResponse.createApiResponse("01", "실패",null);
    }
    return res;
  }

  //상품의 목록을 가져오기.
  @ResponseBody
  @GetMapping("/all")   // get http://localhost:9080/api/products/all
  public ApiResponse<List<Product>> all(){
    ApiResponse<List<Product>> res = null;
    List<Product> products = productSVC.findAll();
    if(products.size() == 0){
      res = ApiResponse.createApiResponse("01", "한 건의 목록도 존재하지 않습니다.", null);
    }else{
      res = ApiResponse.createApiResponse("00", "성공", products);
    }
    return res;
  }

}
