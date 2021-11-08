package com.banln.hwkim.web;

import com.banln.hwkim.common.Constants;
import com.banln.hwkim.common.exception.BanlnHubException;
import com.banln.hwkim.data.dto.ProductDto;
import com.banln.hwkim.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/product-api")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    //@RequiredArgsConstructor로 대체 가능한데 왜 이걸 쓰는걸까??? 롬복이 없어서??
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {
        long startTime = System.currentTimeMillis();
        logger.info("[getProduct] perform {} of Around Hub API.", "getProduct");

        ProductDto productDto = productService.getProduct(productId);
        logger.info("[getProduct] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms",
                productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(),
                productDto.getProductStock(), (System.currentTimeMillis() - startTime));

        return productDto;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping(value = "/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        logger.info("[createProduct] perform {} of Around Hub API.", "createProduct");

        if(productDto.getProductId().equals("") || productDto.getProductId().isEmpty()) {
            logger.error("[createProduct] failed Response :: productId is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }

        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        ProductDto response = productService.saveProduct(productId, productName, productPrice, productStock);
        logger.info("[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
                response.getProductId(), response.getProductName(), response.getProductPrice(), response.getProductStock());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }

    @PostMapping(value = "/product/exception")
    public void exceptionTest() throws BanlnHubException {
        throw new BanlnHubException(Constants.ExceptionClass.PRODUCT, HttpStatus.FORBIDDEN, "접근이 금지되었습니다.");
    }
}
