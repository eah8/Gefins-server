<!DOCTYPE html>

<!-- Author: Einar -->
<!-- Date: November 2017 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>Invalid URL </title>
    </head>
    <body>

        <h1>You have entered an invalid URL</h1>
        <div>
            <h3> <c:out value="|HTTP ${attrs.status} ${attrs.error}|"/> </h3>
            <p> <c:out value="${attrs.message}"/> </p>
        </div>

    </body>

</html>