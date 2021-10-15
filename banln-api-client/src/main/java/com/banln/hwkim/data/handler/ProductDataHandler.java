package com.banln.hwkim.data.handler;

import com.banln.hwkim.data.dao.ProductDao;
import com.banln.hwkim.data.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductDataHandler {
    ProductDao productDao;

    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = new ProductEntity(productId, productName, productPrice, productStock);
        return productDao.saveProduct(productEntity);
    }

    public Optional<ProductEntity> getProductEntity(String productId) {
        return productDao.getProduct(productId);
    }
}
