package com.catwalk.test.Catwalk_Test.controller;

import com.catwalk.test.Catwalk_Test.model.User;
import com.catwalk.test.Catwalk_Test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/rest")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping(value="/user",produces = MediaType.APPLICATION_JSON_VALUE)
  public User save(@RequestBody Map<String,Object> dto){
    ObjectMapper mapper = new ObjectMapper();
    return this.service.save(mapper.convertValue(dto,User.class));
  }

  @GetMapping(value = "/user/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<User> findById(@PathVariable("id") String id ){
    return this.service.findById(Integer.valueOf(id));
  }

  @GetMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<User> findAll(){
    return this.service.findAll();
  }

  @DeleteMapping(value = "/user/{id}")
  public void deleteById(@PathVariable("id") String id ){
   this.service.deleteById(Integer.valueOf(id));
  }

  @ResponseBody
  @GetMapping(value = {"/user/search"}, produces = { "application/json" })
  public Page<User> search(@RequestParam(value = "term",  defaultValue = "") String searchTerm,
                             @RequestParam(value = "page",  defaultValue = "0") Integer page,
                             @RequestParam(value = "limit", defaultValue = "50") Integer limit){
    return this.service.search(searchTerm,PageRequest.of(page,limit));
  }
}
