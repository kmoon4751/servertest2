package com.kh.product.domain.dao;

import com.kh.product.dao.ProductDAO;
import com.kh.product.dao.entity.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Data
@Slf4j
@SpringBootTest
public class ProductDAOImplTest {

  @Autowired
  ProductDAO productDAO;

  @Test
  @DisplayName("등록")
  void save(){
    Product product = new Product();

    product.setPname("민트붕어빵");
    product.setQuantity(3L);
    product.setPrice(2100L);
    Long pid = productDAO.save(product);
    log.info("상품번호={}", pid);
  }

  @Test
  @DisplayName("조회")
  void findById(){
    Optional<Product> product = productDAO.findById(224L);
    if (product.isPresent()) {
      String pname = product.get().getPname();
      log.info("상품이름={}", pname);
    } else {
      log.info("상품을 찾을 수 없음");
    }
  }

  @Test
  @DisplayName("수정")
  void updateById(){
    Long pid = 224L;
    Product product = new Product();
    product.setPname("계란붕어빵");
    product.setQuantity(4L);
    product.setPrice(1800L);
    int updatedRows = productDAO.updateById(pid, product);
    if(updatedRows>0){
      log.info("수정 성공");
    }
  }
  @Test
  @DisplayName("삭제")
  void deleteById(){
    Long pid = 224L;
    int deleteRows = productDAO.deleteById(pid);
    if(deleteRows>0){
      log.info("삭제 성공");
    }
  }

  @Test
  @DisplayName("목록 조회")
  void findAll(){
    List<Product> all = productDAO.findAll();
    log.info("목록={}",all);
  }
}
