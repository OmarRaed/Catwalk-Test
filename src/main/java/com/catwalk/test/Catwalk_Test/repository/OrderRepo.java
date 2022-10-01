package com.catwalk.test.Catwalk_Test.repository;

import com.catwalk.test.Catwalk_Test.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface OrderRepo extends PagingAndSortingRepository<Order, Integer> {
}