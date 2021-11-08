package com.banln.hwkim.data.dao.impl;

import com.banln.hwkim.data.dao.ProductDao;
import com.banln.hwkim.data.entity.ProductEntity;
import com.banln.hwkim.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDaoImpl implements ProductDao {
    private final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    private final ProductRepository productRepository;

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        logger.info("[saveProduct] product 정보 저장. productId : {}", productEntity.getProductId());

        ProductEntity productEntityResult = productRepository.save(productEntity);
        logger.info("[saveProduct] product 정보 저장완료. productId : {}", productEntityResult.getProductId());

        return productEntityResult;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        logger.info("[getProduct] product 정보 요청. productId : {}", productId);

        ProductEntity productEntity = productRepository.findById(productId).orElse(new ProductEntity());
//        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();

        return productEntity;
    }
//    public Optional<ProductEntity> getProduct(String productId) {
//        Optional<ProductEntity> productEntity = productRepository.findById(productId);
//        return productEntity;
//    }

//    // id->repository getOne, getById
//    Optional<User> optional = userRepository.findById(id);
//
//// user -> userApiResponse return
//return optional.map(user -> response(user))
//            .orElseGet(
//                   ()->Header.ERROR("데이터 없음")
//            );
//[출처] [패캠] 스프링부트 강의 - 알게된 것들|작성자 코딩하는 엘모


    /**
     * Repository에서 기본적으로 제공하는 대표적인 메소드
     */
    private void testRepositoryMethod() {
//        productRepository.findById();
//        productRepository.delete();
//        productRepository.deleteAll();
//        productRepository.findAll();
//        productRepository.save();
//        productRepository.saveAll();
    }
}
