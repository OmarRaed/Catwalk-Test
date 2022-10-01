package com.catwalk.test.Catwalk_Test.model;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Scope;

@Scope("test")
class ProductTest {

  @Test
  void shouldCreateProduct(){
    EasyRandom generator = new EasyRandom();
    Assertions.assertNotNull(generator.nextObject(Product.class));
  }

  @Test
  void shouldBuildProduct(){
    Assertions.assertNotNull(Product.builder().build());
  }
}
