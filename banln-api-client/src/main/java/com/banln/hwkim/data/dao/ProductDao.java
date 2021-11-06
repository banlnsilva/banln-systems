package com.banln.hwkim.data.dao;

import com.banln.hwkim.data.entity.ProductEntity;

import java.util.Optional;

public interface ProductDao {
    ProductEntity saveProduct(ProductEntity productEntity);
    ProductEntity getProduct(String productId);
}
