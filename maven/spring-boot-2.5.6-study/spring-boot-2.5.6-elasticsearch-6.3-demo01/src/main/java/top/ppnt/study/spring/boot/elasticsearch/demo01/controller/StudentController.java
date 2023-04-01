package top.ppnt.study.spring.boot.elasticsearch.demo01.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.utils.vo.JsonBean;

import top.ppnt.study.spring.boot.elasticsearch.demo01.model.Student;
import top.ppnt.study.spring.boot.elasticsearch.demo01.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  
  /**
   * 返回测试数据list
   * @return
   */
  @RequestMapping("exampleList")
  public JsonBean<List<Student>> exampleList(){
    List<Student> list = new ArrayList<Student>();
    list.add(new Student("张三", "男", 20));
    list.add(new Student("李四", "女", 21));
    list.add(new Student("王五", "男", 22));
    list.add(new Student("赵六", "男", 23));
    return new JsonBean<>(list);
  }
  /**
   * 批量添加
   * @param students
   * @return
   */
  @PostMapping("/batchAdd")
  public JsonBean<Void> add(@RequestBody List<Student> students) {
    studentRepository.saveAll(students);
    return new JsonBean<>();
  }

  /**
   * 添加
   * @param student
   * @return
   */
  @PostMapping("/add")
  public JsonBean<Void> add(@RequestBody Student student) {
    studentRepository.save(student);
    return new JsonBean<>();
  }

  /**
   * 修改
   * @param student
   * @return
   */
  @PostMapping("/update")
  public JsonBean<Void> updateById(@RequestBody Student student) {
    studentRepository.save(student);
    return new JsonBean<>();
  }

  /**
   * 删除
   * @param id
   * @return
   */
  @PostMapping("/delete/{id}")
  public JsonBean<Void> deleteById(@PathVariable String id) {
    studentRepository.deleteById(id);
    return new JsonBean<>();
  }

  /**
   * 获取所有信息
   * @return
   */
  @GetMapping("/getAll")
  public JsonBean<List<Student>> getAll() {
    Iterable<Student> iterable = studentRepository.findAll();
    List<Student> list = new ArrayList<>();
    iterable.forEach(list::add);
    return new JsonBean<List<Student>>(list);
  }

  /**
   * 查询指定ID
   * @param id
   * @return
   */
  @GetMapping("/get/{id}")
  public JsonBean<Student> getById(@PathVariable String id) {
    if (StringUtils.isEmpty(id)) {
      return new JsonBean<>("id is null");
    }
    Optional<Student> studentOptional = studentRepository.findById(id);
    if (studentOptional.isPresent()) {
      return new JsonBean<>(studentOptional.get());
    }
    return new JsonBean<>(-1, "没有查询到数据");
  }

  /**
   * 普通搜索
   * @param keyword
   * @return
   */
  @GetMapping("/search/name")
  public JsonBean<List<Student>> searchName(String keyword) {
    List<Student> students = studentRepository.findByNameLike(keyword);
    return new JsonBean<List<Student>>(students);
  }

  /**
   * 自定义匹配
   * 普通搜索
   * @param keyword
   * @return
   */
  @GetMapping("/search/name/custom")
  public JsonBean<List<Student>> searchTitleCustom(String keyword) {
    List<Student> students = studentRepository.findByNameCustom(keyword);
    return new JsonBean<List<Student>>(students);
  }
}