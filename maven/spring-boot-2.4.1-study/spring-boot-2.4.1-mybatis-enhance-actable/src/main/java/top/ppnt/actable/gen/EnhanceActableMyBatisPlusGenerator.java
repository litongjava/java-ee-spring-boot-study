package top.ppnt.actable.gen;

import top.ppnt.mybatis.plus.genetator.MyBatisPlusGenerator;

public class EnhanceActableMyBatisPlusGenerator {

  public static void main(String[] args) {
    String author = "Ping E Lee";
    String packageParentName = "top.ppnt.actable";

    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost/mybatis_plus_study?userSSL=true&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
    String jdbcUser = "root";
    String jdbcPswd = "robot_123456#";
    String deleteField="is_del";
    String tablePrefix="t_";
    String[] include= {"user","role"};
    MyBatisPlusGenerator.execute(author, packageParentName, jdbcDriver, jdbcUrl, jdbcUser, jdbcPswd,deleteField,tablePrefix,include);
  }
}