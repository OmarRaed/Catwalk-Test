package com.catwalk.test.Catwalk_Test.service;

import com.catwalk.test.Catwalk_Test.model.Product;
import com.catwalk.test.Catwalk_Test.repository.ProductRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
public class ProductService{

  private final ProductRepo repo;

  public ProductService(ProductRepo repo) {
    this.repo = repo;
  }

  public  Product save(Product item) {
    return repo.save(item);
  }

  public void deleteById(Integer id) {
      repo.deleteById(id);
  }

  public Optional<Product> findById(Integer id) {
    return repo.findById(id);
  }

  public Iterable<Product> findAll() {
    return repo.findAll();
  }

  public Page<Product> search(String term,Pageable pageable) {
    log.info("create a filter in repo for search term {}",term);
    return repo.findAll(pageable);
  }
}
