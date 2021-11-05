package com.banln.hwkim.service.impl;

import com.banln.hwkim.data.dto.ProductDto;
import com.banln.hwkim.data.entity.ProductEntity;
import com.banln.hwkim.data.handler.ProductDataHandler;
import com.banln.hwkim.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    ProductDataHandler productDataHandler;

    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);
        ProductDto productDto = new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
                productEntity.getProductPrice(), productEntity.getProductStock());

        return productDto;
    }

    public ProductDto getProduct(String productId) {
        Optional<ProductEntity> productEntity = productDataHandler.getProductEntity(productId);
        ProductDto productDto = new ProductDto(productEntity.getProductId);

    }
}
