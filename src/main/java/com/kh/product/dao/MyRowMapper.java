package com.kh.product.dao;

import com.kh.product.dao.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyRowMapper implements RowMapper<Product> {
  @Override
  public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    Product product = new Product();
    product.setPid(rs.getLong("pid"));
    product.setPname(rs.getString("pname"));
    product.setQuantity(rs.getLong("quantity"));
    product.setPrice(rs.getLong("price"));

    return product;
  }
}
