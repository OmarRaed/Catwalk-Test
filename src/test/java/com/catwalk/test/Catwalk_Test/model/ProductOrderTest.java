package com.catwalk.test.Catwalk_Test.model;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Scope;

@Scope("test")
class ProductOrderTest {

  @Test
  void shouldCreateProductOrder(){
    EasyRandom generator = new EasyRandom();
    Assertions.assertNotNull(generator.nextObject(ProductOrder.class));
  }

  @Test
  void shouldBuildProductOrder(){
    Assertions.assertNotNull(ProductOrder.builder().build());
  }
}
