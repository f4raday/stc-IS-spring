package com.f4raday.service.interfaces;

import com.f4raday.model.Product;

import java.util.List;

public interface IProductService {
    void save(Product product);
    List<Product> findAll();
    void delete(Long id);
}
