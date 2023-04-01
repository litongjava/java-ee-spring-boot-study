package com.litong.spring.boot.v158.layui.v255.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litong.spring.boot.v158.layui.v255.entity.FormComplaint;
import com.litong.spring.boot.v158.layui.v255.service.FormComplaintService;
import com.litong.spring.boot.v158.mp.layui.v255.controller.MpCurdController;

/**
 * <p>
 * 投诉单 前端控制器
 * </p>
 *
 * @author litong
 * @since 2020-06-01
 */
@RestController
@RequestMapping("/formComplaint")
public class FormComplaintController extends MpCurdController<FormComplaintService,FormComplaint> {

  
}
