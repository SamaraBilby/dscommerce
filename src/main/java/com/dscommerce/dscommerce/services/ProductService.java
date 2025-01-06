package com.dscommerce.dscommerce.services;

import com.dscommerce.dscommerce.dto.ProductDTO;
import com.dscommerce.dscommerce.entities.Product;
import com.dscommerce.dscommerce.repositories.ProductRepository;
import com.dscommerce.dscommerce.services.exceptions.ResourceNotFoundException;
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
        Product product = _productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return  new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = _productRepository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public  ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(dto,entity);
        entity = _productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public  ProductDTO update(Long id, ProductDTO dto){
        Product entity = _productRepository.getReferenceById(id);
        copyDtoToEntity(dto,entity);
        entity = _productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        _productRepository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
