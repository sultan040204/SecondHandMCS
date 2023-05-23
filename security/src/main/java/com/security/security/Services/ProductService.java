package com.security.security.Services;


import com.security.security.Entities.ProductEntity;
import com.security.security.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
@Autowired
    private ProductRepository productRepository;

    public ProductEntity findByName(ProductEntity product){
        String productWord = product.getName();
        System.out.println(productRepository.findByName(productWord));
        return productRepository.findByName(productWord);
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> findById(Integer id) {
        return productRepository.findById(id);
    }

    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    public ProductEntity update(Integer id, ProductEntity product){
        ProductEntity real = productRepository.findById(id).get();
        if (product.getName()!=null)
            real.setName(product.getName());
        if (product.getId()!=null)
            real.setId(product.getId());
        if (product.getUserId()!=null)
            real.setUserId(product.getUserId());
        return productRepository.save(real);
    }


    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
