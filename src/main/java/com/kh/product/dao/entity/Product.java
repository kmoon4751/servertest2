package com.kh.product.dao.entity;

import lombok.Data;

@Data
public class Product {
  private Long pid;          //상품아이디
  private String pname;     //상품명
  private Long quantity;     //상품수량
  private Long price;        //상품가격
}
