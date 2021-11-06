package com.banln.hwkim.data.handler.impl;

import com.banln.hwkim.data.dao.ProductDao;
import com.banln.hwkim.data.entity.ProductEntity;
import com.banln.hwkim.data.handler.ProductDataHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductDataHandlerImpl implements ProductDataHandler {
    private final Logger logger = LoggerFactory.getLogger(ProductDataHandlerImpl.class);
    private final ProductDao productDao;

//    @Autowired
//    public ProductDataHandlerImpl(ProductDAO productDAO) {
//        this.productDAO = productDAO;
//    }

    @Override
    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {
        logger.debug("[saveProductEntity] 매개변수를 통해 Entity 객체 생성");
        ProductEntity productEntity = new ProductEntity(productId, productName, productPrice, productStock);
        logger.info("[saveProductEntity] productDAO로 Product 정보 저장 요청. productId : {}", productId);

        return productDao.saveProduct(productEntity);
    }

    @Override
    public ProductEntity getProductEntity(String productId) {
        logger.info("[saveProductEntity] productDAO로 Product 정보 요청. productId : {}", productId);
        return productDao.getProduct(productId);
    }
}
