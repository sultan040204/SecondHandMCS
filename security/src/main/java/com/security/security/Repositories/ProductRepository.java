package com.security.security.Repositories;

import com.security.security.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
  ProductEntity findByName(String name);
  @Query(value = "select * from Product p where p.name like %:keyword%", nativeQuery = true)
  List<ProductEntity> findByKeyword(@Param("keyword") String keyword);
  }
