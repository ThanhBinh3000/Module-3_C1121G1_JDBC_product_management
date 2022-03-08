<%--
  Created by IntelliJ IDEA.
  User: quan
  Date: 04/03/2022
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a href="/categories">Danh sách danh mục sản phẩm</a>
    <h1>Xóa danh mục sản phẩm</h1>
    <form action="/categories?action=delete&id=${category.id}" method="post">
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Tên danh mục sản phẩm:</label>
            <input type="text" class="form-control" id="exampleInputPassword1" name="name" value="${category.name}" disabled>
        </div>
        <button type="submit" class="btn btn-danger">Xóa</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
