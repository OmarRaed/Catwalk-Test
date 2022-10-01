package com.catwalk.test.Catwalk_Test.controller;

import com.catwalk.test.Catwalk_Test.model.Order;
import com.catwalk.test.Catwalk_Test.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/rest")
public class OrderController {

  private final OrderService service;

  public OrderController(OrderService service) {
    this.service = service;
  }

  @PostMapping(value="/order",produces = MediaType.APPLICATION_JSON_VALUE)
  public Order save(@RequestBody Map<String,Object> dto){
    ObjectMapper mapper = new ObjectMapper();
    return this.service.save(mapper.convertValue(dto,Order.class));
  }

  @GetMapping(value = "/order/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<Order> findById(@PathVariable("id") String id ){
    return this.service.findById(Integer.valueOf(id));
  }

  @GetMapping(value = "/order",produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Order> findAll(){
    return this.service.findAll();
  }

  @DeleteMapping(value = "/order/{id}")
  public void deleteById(@PathVariable("id") String id ){
   this.service.deleteById(Integer.valueOf(id));
  }

  @ResponseBody
  @GetMapping(value = {"/order/search"}, produces = { "application/json" })
  public Page<Order> search(@RequestParam(value = "term",  defaultValue = "") String searchTerm,
                             @RequestParam(value = "page",  defaultValue = "0") Integer page,
                             @RequestParam(value = "limit", defaultValue = "50") Integer limit){
    return this.service.search(searchTerm,PageRequest.of(page,limit));
  }
}
