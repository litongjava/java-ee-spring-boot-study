<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="UTF-8">
  <title>添加用户</title>
</head>
<body>
<form th:action="@{/save}" method="post">
  用户姓名：<input type="text" name="name" />
  <!-- 在名字右侧显示效验结果 -->
  <font color="red" th:errors="${users.name}"></font>
  <br/>
  用户密码：<input type="password" name="password" />
  <font color="red" th:errors="${users.password}"></font>
  <br/>
  用户年龄：<input type="text" name="age" />
  <font color="red" th:errors="${users.age}"></font>
  <br/>
  用户邮箱：<input type="text" name="email" />
  <font color="red" th:errors="${users.email}"></font>
  <br/>
  <input type="submit" value="OK"/>
</form>
</body>
</html>