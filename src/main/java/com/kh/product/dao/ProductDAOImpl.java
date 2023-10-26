package com.kh.product.dao;


import com.kh.product.dao.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository //DAO역할 클래스
@RequiredArgsConstructor  //final 멤버필드를 매개값으로 가지는 생성자를 자동 생성
public class ProductDAOImpl implements ProductDAO {

  private final NamedParameterJdbcTemplate template;
// NamedParameterJdbcTemplate 스프링 프레임워크 클래스 중 하나
// 명명된 파라미터를 사용하여 SQL 쿼리를 실행하고, 데이터베이스와 상호 작용하기 위한 다양한 메서드를 제공.
// template라는 이름의 NamedParameterJdbcTemplate 객체를 나타낸다.

  @Override
  public Long save(Product product){
    StringBuffer sql = new StringBuffer();
    sql.append("insert into product(pid, pname, quantity, price) ");
    sql.append("values(p_p_id_seq.nextval, :pname, :quantity, :price) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(product);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(),param,keyHolder,new String[]{"pid"});

    long pid = keyHolder.getKey().longValue();  // 상품 아이디
    return pid;
  }
  private RowMapper<Product> productRowMapper(){
    return (rs, rowNum)->{
      Product product = new Product();
      product.setPid(rs.getLong("pid"));
      product.setPname(rs.getString("pname"));
      product.setQuantity(rs.getLong("quantity"));
      product.setPrice(rs.getLong("price"));

      return product;
    };
  }

  @Override
  public Optional<Product> findById(Long pid) {
      StringBuffer sql = new StringBuffer();
      sql.append("select pid, pname, quantity, price ");
      sql.append(" from product ");
      sql.append(" where pid = :id ");

      MyRowMapper myRowMapper = new MyRowMapper();

      try{

      Map<String, Long> param = Map.of("id",pid);

      Product product = template.queryForObject(sql.toString(), param, myRowMapper);
      return Optional.of(product);
    }catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Product> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append(" select pid, pname, quantity, price ");
    sql.append(" from product ");
    sql.append(" order by pid desc");

    // 자동매핑
    List<Product> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Product.class));

    return list;
  }

  @Override
  public int deleteById(Long pid) {
    String sql = "delete from product where pid = :pid";

    int deletedRowCnt = template.update(sql, Map.of("pid", pid));
    return deletedRowCnt;
  }

  @Override
  public int updateById(Long pid, Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append("update product ");
    sql.append(" set pname = :pname, quantity = :quantity, price = :price");
    sql.append(" where pid = :pid ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("pid", pid)
        .addValue("pname", product.getPname())
        .addValue("quantity", product.getQuantity())
        .addValue("price", product.getPrice());

    int updatedRows = template.update(sql.toString(),param);
    return updatedRows;
  }
}



