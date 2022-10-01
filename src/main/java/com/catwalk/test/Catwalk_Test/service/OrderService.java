package com.catwalk.test.Catwalk_Test.service;

import com.catwalk.test.Catwalk_Test.model.Order;
import com.catwalk.test.Catwalk_Test.repository.OrderRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
public class OrderService{

  private final OrderRepo repo;

  public OrderService(OrderRepo repo) {
    this.repo = repo;
  }

  public  Order save(Order item) {
    return repo.save(item);
  }

  public void deleteById(Integer id) {
      repo.deleteById(id);
  }

  public Optional<Order> findById(Integer id) {
    return repo.findById(id);
  }

  public Iterable<Order> findAll() {
    return repo.findAll();
  }

  public Page<Order> search(String term,Pageable pageable) {
    log.info("create a filter in repo for search term {}",term);
    return repo.findAll(pageable);
  }
}
