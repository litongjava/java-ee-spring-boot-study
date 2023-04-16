package com.litongjava.study.spring.boot.elmentui.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litongjava.study.spring.boot.elmentui.entity.Contact;
import com.litongjava.study.spring.boot.elmentui.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 联系人 前端控制器
 * </p>
 *
 * @author litongjava
 * @since 2023-04-12
 */
@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {


  @Autowired
  private ContactService contactService;

  @GetMapping(value = "/")
  public R<Page<Contact>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
    if (current == null) {
      current = 1;
    }
    if (pageSize == null) {
      pageSize = 10;
    }
    Page<Contact> aPage = contactService.page(new Page<>(current, pageSize));
    return R.ok(aPage);
  }

  @GetMapping(value = "/{id}")
  public R<Contact> getById(@PathVariable("id") String id) {
    return R.ok(contactService.getById(id));
  }

  @PostMapping(value = "/create")
  public R<Boolean> create(@RequestBody Contact params) {
    boolean save = contactService.save(params);
    return R.ok(save);
  }

  @PostMapping(value = "/delete/{id}")
  public R<Boolean> delete(@PathVariable("id") String id) {
    boolean b = contactService.removeById(id);
    return R.ok(b);
  }

  @PostMapping(value = "/update")
  public R<Boolean> delete(@RequestBody Contact params) {
    boolean b = contactService.updateById(params);
    return R.ok(b);
  }
}
