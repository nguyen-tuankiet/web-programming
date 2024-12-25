<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Header Menu with Submenu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>

    <style>
        #container{
            display: flex;
            flex-direction: column;
        }

        #home_header{
            height: 70px;
            width: 100%;
            margin-bottom: 20px;
        }

    </style>

</head>


<body id="container">

    <div id="home_header">
        <jsp:include page="header.jsp"/>
    </div>


    <div id="home_body">
        <jsp:include page="bodyHome.jsp"/>
    </div>


<script src="${pageContext.request.contextPath}/static/style-page/home/home.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</body>

