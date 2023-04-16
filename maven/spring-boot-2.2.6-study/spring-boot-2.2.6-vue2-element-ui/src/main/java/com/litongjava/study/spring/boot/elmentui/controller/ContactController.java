package com.litongjava.study.spring.boot.elmentui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litongjava.study.spring.boot.elmentui.entity.Contact;
import com.litongjava.study.spring.boot.elmentui.service.ContactService;

/**
 * <p>
 * 联系人 前端控制器
 * </p>
 *
 * @author litongjava
 * @since 2023-04-12
 */
@Controller
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {


  @Autowired
  private ContactService contactService;

  @GetMapping(value = "/")
  public ResponseEntity<Page<Contact>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
    if (current == null) {
      current = 1;
    }
    if (pageSize == null) {
      pageSize = 10;
    }
    Page<Contact> aPage = contactService.page(new Page<>(current, pageSize));
    return new ResponseEntity<>(aPage, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Contact> getById(@PathVariable("id") String id) {
    return new ResponseEntity<>(contactService.getById(id), HttpStatus.OK);
  }

  @PostMapping(value = "/create")
  public ResponseEntity<Object> create(@RequestBody Contact params) {
    contactService.save(params);
    return new ResponseEntity<>("created successfully", HttpStatus.OK);
  }

  @PostMapping(value = "/delete/{id}")
  public ResponseEntity<Object> delete(@PathVariable("id") String id) {
    contactService.removeById(id);
    return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
  }

  @PostMapping(value = "/update")
  public ResponseEntity<Object> delete(@RequestBody Contact params) {
    contactService.updateById(params);
    return new ResponseEntity<>("updated successfully", HttpStatus.OK);
  }
}
