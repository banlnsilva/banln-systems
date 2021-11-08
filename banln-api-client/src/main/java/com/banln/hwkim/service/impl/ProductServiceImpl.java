package com.banln.hwkim.service.impl;

import com.banln.hwkim.data.dto.ProductDto;
import com.banln.hwkim.data.entity.ProductEntity;
import com.banln.hwkim.data.handler.ProductDataHandler;
import com.banln.hwkim.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    ProductDataHandler productDataHandler;

    @Autowired
    public ProductServiceImpl(ProductDataHandler productDataHandler) {
        this.productDataHandler = productDataHandler;
    }

    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        logger.info("[saveProduct] productDataHandler 로 상품 정보 저장 요청");
        ProductEntity productEntity = productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);

        logger.info("[saveProduct] Entity 객체를 DTO 객체로 변환 작업. productId : {}", productEntity.getProductId());
        ProductDto productDto = new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
                productEntity.getProductPrice(), productEntity.getProductStock());

        return productDto;
    }

    public ProductDto getProduct(String productId) {
        logger.info("[getProduct] productDataHandler 로 상품 정보 조회 요청");
        ProductEntity productEntity = productDataHandler.getProductEntity(productId);

        logger.info("[getProduct] Entity 객체를 DTO 객체로 변환 작업. productId : {}", productEntity.getProductId());
        ProductDto productDto = new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
                productEntity.getProductPrice(), productEntity.getProductStock());

        return productDto;
    }
}
