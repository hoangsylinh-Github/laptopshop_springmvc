<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Latest compiled JavaScript -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <!-- <link rel="stylesheet" href="/css/demo.css"> -->
        </head>

        <body>
            <h2>
                <!-- /*jstl*/ -->
                <!-- <c:out value="${hoanglinh}" /> -->

                <!-- jsp -->
                ${hoanglinh1}
                create successfully!
            </h2>

            <h3>
                ${linhchuot}
            </h3>
            <button class="btn btn-success">Submit</button>
        </body>

        </html>