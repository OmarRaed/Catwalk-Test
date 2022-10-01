package com.catwalk.test.Catwalk_Test.controller;

import com.catwalk.test.Catwalk_Test.model.Product;
import com.catwalk.test.Catwalk_Test.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/rest")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @PostMapping(value="/product",produces = MediaType.APPLICATION_JSON_VALUE)
  public Product save(@RequestBody Map<String,Object> dto){
    ObjectMapper mapper = new ObjectMapper();
    return this.service.save(mapper.convertValue(dto,Product.class));
  }

  @GetMapping(value = "/product/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<Product> findById(@PathVariable("id") String id ){
    return this.service.findById(Integer.valueOf(id));
  }

  @GetMapping(value = "/product",produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Product> findAll(){
    return this.service.findAll();
  }

  @DeleteMapping(value = "/product/{id}")
  public void deleteById(@PathVariable("id") String id ){
   this.service.deleteById(Integer.valueOf(id));
  }

  @ResponseBody
  @GetMapping(value = {"/product/search"}, produces = { "application/json" })
  public Page<Product> search(@RequestParam(value = "term",  defaultValue = "") String searchTerm,
                             @RequestParam(value = "page",  defaultValue = "0") Integer page,
                             @RequestParam(value = "limit", defaultValue = "50") Integer limit){
    return this.service.search(searchTerm,PageRequest.of(page,limit));
  }
}
