package com.f4raday.service;

import com.f4raday.model.Product;
import com.f4raday.repository.ProductRepository;
import com.f4raday.service.interfaces.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    public void save(Product product) {
        logger.debug(String.format("Product %s added to the databse"), product.getDescription());
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        logger.debug(String.format("Deleting product with id %d", id));
        productRepository.delete(id);
    }

}
