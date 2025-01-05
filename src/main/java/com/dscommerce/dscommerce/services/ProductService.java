package com.dscommerce.dscommerce.services;

import com.dscommerce.dscommerce.dto.ProductDTO;
import com.dscommerce.dscommerce.entities.Product;
import com.dscommerce.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository _productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> result = _productRepository.findById(id);
        Product product = result.get();
        return  new ProductDTO(product);
    }
}
