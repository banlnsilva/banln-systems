package com.banln.hwkim.service;

import com.banln.hwkim.data.dto.ProductDto;
import com.banln.hwkim.data.entity.ProductEntity;
import com.banln.hwkim.data.handler.ProductDataHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    ProductDataHandler productDataHandler;

    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);
        ProductDto productDto = new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
                productEntity.getProductPrice(), productEntity.getProductStock());

        return productDto;
    }

    public ProductDto getProduct(String productId) {
        Optional<ProductEntity> productEntity = productDataHandler.getProductEntity(productId);
        ProductDto productDto = new ProductDto(productEntity.getProductId)

    }
}

