package com.dscommerce.dscommerce.controllers;

import com.dscommerce.dscommerce.dto.ProductDTO;
import com.dscommerce.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService _productService;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return _productService.findById(id);
    }

    @GetMapping
    public Page<ProductDTO> findById(Pageable pageable) {
        return _productService.findAll(pageable);
    }
}
