<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title></title>
    <%@ include file="head.jsp" %>
</head>
<body>
<script type="text/javascript">
    window.parent.uploadForm.success(${result});
</script>
</body>
</html>
