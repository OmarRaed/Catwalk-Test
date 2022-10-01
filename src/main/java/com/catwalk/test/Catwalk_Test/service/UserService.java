package com.catwalk.test.Catwalk_Test.service;

import com.catwalk.test.Catwalk_Test.model.User;
import com.catwalk.test.Catwalk_Test.repository.UserRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
public class UserService{

  private final UserRepo repo;

  public UserService(UserRepo repo) {
    this.repo = repo;
  }

  public  User save(User item) {
    return repo.save(item);
  }

  public void deleteById(Integer id) {
      repo.deleteById(id);
  }

  public Optional<User> findById(Integer id) {
    return repo.findById(id);
  }

  public Iterable<User> findAll() {
    return repo.findAll();
  }

  public Page<User> search(String term,Pageable pageable) {
    log.info("create a filter in repo for search term {}",term);
    return repo.findAll(pageable);
  }
}
