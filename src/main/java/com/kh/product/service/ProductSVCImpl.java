package com.kh.product.service;

import com.kh.product.dao.ProductDAO;
import com.kh.product.dao.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC {

  private final ProductDAO productDAO;
  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }

  @Override
  public Optional<Product> findById(Long pid) {
    return productDAO.findById(pid);
  }

  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }

  @Override
  public int deleteById(Long pid) {
    return productDAO.deleteById(pid);
  }

  @Override
  public int updateById(Long pid, Product product) {
    return productDAO.updateById(pid, product);
  }
}
