package com.catwalk.test.Catwalk_Test.model;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Scope;

@Scope("test")
class OrderTest {

  @Test
  void shouldCreateOrder(){
    EasyRandom generator = new EasyRandom();
    Assertions.assertNotNull(generator.nextObject(Order.class));
  }

  @Test
  void shouldBuildOrder(){
    Assertions.assertNotNull(Order.builder().build());
  }
}
