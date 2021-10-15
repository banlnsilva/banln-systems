package com.banln.hwkim.data.repository;

import com.banln.hwkim.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
