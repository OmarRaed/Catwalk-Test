package com.catwalk.test.Catwalk_Test.repository;

import com.catwalk.test.Catwalk_Test.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
}