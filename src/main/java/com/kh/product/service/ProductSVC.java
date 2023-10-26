package com.kh.product.service;

import com.kh.product.dao.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductSVC {

  Long save(Product product);

  Optional<Product> findById(Long pid);

  List<Product> findAll();

  int deleteById(Long pid);

  int updateById(Long pid, Product product);

}
