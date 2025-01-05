package com.dscommerce.dscommerce.services;

import com.dscommerce.dscommerce.dto.ProductDTO;
import com.dscommerce.dscommerce.entities.Product;
import com.dscommerce.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = _productRepository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }
}
