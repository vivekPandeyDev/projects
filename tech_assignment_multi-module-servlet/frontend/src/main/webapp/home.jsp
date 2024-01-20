<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Customer</title>
</head>
<body>
<div class="container-md">

                                        <c:forEach items="${users}" var="user">
                        <h1>${user.userName}  <a href="/frontend/user?userId=${user.userId}">${user.userName}</a></h1>
                                        </c:forEach>

</div>
</body>
</html>



