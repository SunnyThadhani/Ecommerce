package com.order.ecommerce.service;

import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.model.Product;
import com.order.ecommerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public Product getProduct(String productId) {
        log.info("Get Product by productId {}", productId);
        return productRepository.findById(productId).orElseThrow();
    }

    public Product createProduct(ProductDto productDto) {
        log.info("Creating Product with productId {}", productDto.getProductId());
        return productRepository.save(toProductEntity(productDto));
    }

    private Product toProductEntity(ProductDto productDto) {

        Product product=new Product();

        product.setProductId(productDto.getProductId());
        product.setSku(productDto.getSku());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(LocalDate.now());
        product.setOrderItems(null);

        return  product;
    }

    public Product updateProduct(String productId, ProductDto productDto) {
        Product product=productRepository.findById(productId).orElseThrow();

        product=updateProductEntity(product,productDto);
        return productRepository.save(product);
    }

    private Product updateProductEntity(Product product, ProductDto productDto) {
        if(productDto.getTitle()!=null
                && !productDto.getTitle().isEmpty()){
            product.setTitle(productDto.getTitle());
        }
        else if(productDto.getSku()!=null
                && !productDto.getSku().isEmpty()){
            product.setSku(productDto.getSku());
        }
        else if(productDto.getDescription()!=null
                && !productDto.getDescription().isEmpty()){
            product.setDescription(productDto.getDescription());
        }
        else if(productDto.getPrice()!=null){
            product.setPrice(productDto.getPrice());
        }

        return product;
    }
}
