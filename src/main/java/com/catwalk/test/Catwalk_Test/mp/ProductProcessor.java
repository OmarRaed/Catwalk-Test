package com.catwalk.test.Catwalk_Test.mp;

import com.catwalk.test.Catwalk_Test.model.Product;
import com.catwalk.test.Catwalk_Test.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;


@Slf4j
@Component
public class ProductProcessor {
  private final ObjectMapper mapper;
  private final ProductService service;

  public ProductProcessor(ProductService service) {
    this.service = service;
    this.mapper = new ObjectMapper();
  }

  @Bean
  public Function<Map<String,Object>, Product> processProduct() {
    return map -> {
      log.info("processing: {}", map);
      return service.save(mapper.convertValue(map,Product.class));
    };
  }

}

