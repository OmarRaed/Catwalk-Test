package com.catwalk.test.Catwalk_Test.repository;

import com.catwalk.test.Catwalk_Test.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product, Integer> {
}