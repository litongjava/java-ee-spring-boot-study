package com.litong.spring.boot.v158.layui.v255.service;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.litong.spring.boot.v158.layui.v255.App;

/**
 * @author bill robot
 * @date 2020年6月9日_上午9:27:26 
 * @version 1.0 
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class FormComplaintServiceTest {

  @Autowired
  private FormComplaintService fcs;

  @Test
  public void testRemoveByIds() {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(4);
    list.add(5);
    fcs.removeByIds(list);
  }
}
