package com.banln.hwkim.service;

import com.banln.hwkim.data.dto.ProductDto;

public interface ProductService {
    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock);
    public ProductDto getProduct(String productId);
}
