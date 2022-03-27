import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.litong.spring.boot.v158.layui.v255.ImprotDao;
import com.litong.spring.boot.v158.layui.v255.dao.UserDao;
import com.litong.spring.boot.v158.layui.v255.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImprotDao.class)
public class SampleTest {

  @Autowired
  private UserDao userDao;

  @Test
  public void testSelect() {
    System.out.println(("----- selectAll method test ------"));
    List<User> userList = userDao.selectList(null);
    Assert.assertEquals(5, userList.size());
    userList.forEach(System.out::println);
  }
}