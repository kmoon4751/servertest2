package com.kh.product.dao;

import com.kh.product.dao.entity.Product;

import java.util.List;
import java.util.Optional;

//추상적 메소드 구현
public interface ProductDAO {

  Long save(Product product);

  Optional<Product> findById(Long pid);

  List<Product> findAll();

  int deleteById(Long pid);

  int updateById(Long pid, Product product);
}
