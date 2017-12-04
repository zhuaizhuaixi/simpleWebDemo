<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ddddd</title>
    <script type="text/javascript" src="/js/common/jquery-3.2.1.js"></script>
</head>
<body>
<input type="text" id="text" oninput="realTimeSearch()">

</body>
<script type="text/javascript">
    function realTimeSearch() {
        console.log($('#text').val())

    }
</script>
</html>
