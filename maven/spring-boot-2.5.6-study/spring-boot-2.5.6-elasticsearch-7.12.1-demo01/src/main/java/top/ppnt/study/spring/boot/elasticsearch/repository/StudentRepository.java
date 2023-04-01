package top.ppnt.study.spring.boot.elasticsearch.repository;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import top.ppnt.study.spring.boot.elasticsearch.model.Student;
public interface StudentRepository extends ElasticsearchRepository<Student, String> {

  /**
   * 通过姓名模拟查询学生信息
   * @param keyword
   * @return
   */
  List<Student> findByNameLike(String keyword);

  /**
   * 自定义查询，固定匹配查询学生信息
   * @param keyword
   * @return
   */
  @Query("{\"match_phrase\":{\"name\":\"?0\"}}")
  List<Student> findByNameCustom(String keyword);
}