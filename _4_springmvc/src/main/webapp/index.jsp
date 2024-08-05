<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
  <meta charset="UTF-8">
  <title>首页</title>
  <style type="text/css">
    body{margin:0;overflow:hidden}.main-circle{position:relative;top:170px;margin:0 auto;width:400px;height:400px;border-radius:50%;box-shadow:0 0 20px 1px;animation:rotate 4s linear infinite;background-color:#fff}@keyframes rotate{0%{transform:rotate(0)}100%{transform:rotate(360deg)}}.main-circle::after{content:'';position:absolute;top:0;right:0;width:200px;height:400px;border-radius:0 200px 200px 0;background-color:#000}.circle{position:absolute;left:100px;width:200px;height:200px;border-radius:50%}.black{top:0;background-color:#000}.white{z-index:1;top:200px;background-color:#fff}.black::after,.white::after{z-index:2;content:'';position:absolute;top:65px;left:65px;width:70px;height:70px;border-radius:50%}.black::after{background-color:#fff}.white::after{background-color:#000}.eight{position:absolute;top:-150px;left:125px;width:150px;height:100px;background:#000;transform-origin:75px 350px}.eight::after,.eight::before{position:absolute;left:0;width:150px;height:20px;background-color:#fff;content:''}.eight::before{top:20px}.eight::after{top:60px}.a{transform:rotate(0)}.b{transform:rotate(45deg)}.c{transform:rotate(90deg)}.d{transform:rotate(135deg)}.e{transform:rotate(180deg)}.f{transform:rotate(-135deg)}.g{transform:rotate(-90deg)}.h{transform:rotate(-45deg)}.seven{position:absolute;right:65px;width:20px;background:#fff}.eight-1{top:40px;height:20px}.eight-2{height:100px}.eight-3{bottom:0;height:20px}.eight-5{top:0;height:20px}.eight-5::before{content:'';position:absolute;top:80px;width:20px;height:20px;background-color:#fff}.eight-6{height:60px;top:0}.eight-7{height:60px;bottom:0}.eight-8{height:20px;top:0};
    table{width:100%;border-collapse:collapse}td,th{padding:10px;}th{background-color:#f2f2f2;font-weight:700}tr:nth-child(even){background-color:#f5f5f5}table,td,th{border:1px solid #000}
  </style>
  <script type="javascript" src="<%= request.getContextPath() %>/static/js/jquery.min.js"></script>
  <script type="javascript">
    $(document).ready(function() {
      $('#hello-xml-1').click(function() {
        $.ajax({
          type: 'GET',
          url: '<%= request.getContextPath() %>/test/helloxml',
          accpet: 'application/json',
          data: JSON.stringify(formData),
          success: function(response) {
            $('#response').text(JSON.stringify(response));
          }
        });
      });
    });
  </script>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>index</th>
    <th>功能描述</th>
    <th>来源/路径</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td>1</td>
    <td>不带参数请求，直接返回一个hashmap。</td>
    <td>TestOtherController: /test/hello</td>
    <td><a href="test/hello" target="_blank">演示</a></td>
  </tr>
  <tr>
    <td>2</td>
    <td>使用后端包装请求转发模拟效果，并且设置请求头 'Accept: application/json'，查看返回效果</td>
    <td>TestOtherController: test/xmlOrJson?accept=application/json</td>
    <td><a href="test/xmlOrJson?accept=application/json" target="_blank">演示</a></td>
  </tr>
  <tr>
    <td>3</td>
    <td>使用后端包装请求转发模拟效果，并且设置请求头 'Accept: application/xml'，查看返回效果</td>
    <td>TestOtherController: test/xmlOrJson?accept=application/xml</td>
    <td><a href="test/xmlOrJson?accept=application/xml" target="_blank">演示</a></td>
  </tr>
  <tr>
    <td>4</td>
    <td>消息转换器</td>
    <td>TestMessageConverterController: /msg/convert/index</td>
    <td><a href="/msg/convert/index" target="_blank">演示</a></td>
  </tr>
  <tr>
    <td>5</td>
    <td>连接数据库</td>
    <td>TestDBController: /db/query</td>
    <td><a href="db/query" target="_blank">演示</a></td>
  </tr>
  <!-- 更多功能项 -->
  </tbody>
</table>
<div class="main-circle" style="margin-top: 200px">
  <div class="circle black"></div>
  <div class="circle white"></div>
  <div class="eight a"><div class="seven eight-1"></div></div>
  <div class="eight b"><div class="seven eight-2"></div></div>
  <div class="eight c"><div class="seven eight-3"></div></div>
  <div class="eight d"><div class="seven eight-4"></div></div>
  <div class="eight e"><div class="seven eight-5"></div></div>
  <div class="eight f"><div class="seven eight-6"></div></div>
  <div class="eight g"><div class="seven eight-7"></div></div>
  <div class="eight h"><div class="seven eight-8"></div></div>
</div>
</body>
</html>
