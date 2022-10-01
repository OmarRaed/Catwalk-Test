package com.catwalk.test.Catwalk_Test.mp;

import com.catwalk.test.Catwalk_Test.model.User;
import com.catwalk.test.Catwalk_Test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;


@Slf4j
@Component
public class UserProcessor {
  private final ObjectMapper mapper;
  private final UserService service;

  public UserProcessor(UserService service) {
    this.service = service;
    this.mapper = new ObjectMapper();
  }

  @Bean
  public Function<Map<String,Object>, User> processUser() {
    return map -> {
      log.info("processing: {}", map);
      return service.save(mapper.convertValue(map,User.class));
    };
  }

}

