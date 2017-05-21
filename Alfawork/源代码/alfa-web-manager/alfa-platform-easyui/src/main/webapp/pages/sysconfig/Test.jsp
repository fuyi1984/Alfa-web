<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/21
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../../easyui/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../../easyui/themes/icon.css"/>

    <script src="../../easyui/jquery.min.js" type="text/javascript"></script>
    <script src="../../easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../../easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $("button").click(function () {
                $.ajax({
                    url:'/alfa-ws/rest/Sysconfig/findlist',                
					type:"post",
					contentType:'application/json;charset=UTF-8',
                    success:function(result){
                        alert(reuslt);
                    }
                });
            })
        })
    </script>
</head>
<body>
    <div></div>
    <button>修改内容</button>
</body>
</html>
