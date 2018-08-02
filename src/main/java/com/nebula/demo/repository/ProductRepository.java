package com.nebula.demo.repository;

import com.nebula.demo.entity.Product;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@CacheConfig(cacheNames = "products")
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Override
    @Cacheable(key = "#prod")
    public List<Product> findAll();

    @Cacheable(key = "#p0")
    public Product findById(Long id);

}
