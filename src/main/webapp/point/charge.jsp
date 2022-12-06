<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>포인트 충전</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    <Script>
        $(document).ready(function(){

            $('head').load('../importer.html');
        });



    </Script>
    <style>
    html,
      body {
        width: 100%;
        height: 100%;
      }
      .container {
        width: 100%;
        height: 100%;
        }
      .center{

        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
      }

    </style>
</head>
<body>
<!--NAV 바 -->
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"><b>크라우드 펀딩</b></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Dropdown
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <button class="nav-link btn" onclick="moveToLogIn()">로그인/회원가입</button>
                </li>
                <li class="nav-item">
                    <button class="nav-link btn" onclick="moveToShow()">0000</button>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>

        </div>
    </div>
</nav>
<!--button -->
<body>
<div class="container">
    <div class="center">
        <a href="#" class="btn btn-outline-primary" style="block;width:100px;height:150px;margin:10px;" onclick="chargePoint1   ()"><b>1,000원
            충전</b></a>
        <a href="#" class="btn btn-outline-primary text-align-center"
           style="block;width:100px;height:150px;margin:10px;" onclick="chargePoint(5000)"><b>5,000원 충전</b></a>
        <a href="#" class="btn btn-outline-primary text-align-center"
           style="block;width:100px;height:150px;margin:10px;" onclick="chargePoint(10000)"><b>10,000원 충전</b></a><br/>
        <a href="#" class="btn btn-outline-primary text-align-center"
           style="block;width:100px;height:150px;margin:10px;" onclick="chargePoint(50000)"><b>50,000원 충전</b></a>
        <a href="#" class="btn btn-outline-primary text-align-center"
           style="block;width:100px;height:150px;margin:10px;" onclick="chargePoint(100000)"><b>100,000원 충전</b></a>
        <a href="#" class="btn btn-outline-primary text-align-center"
           style="block;width:100px;height:150px;margin:10px;"><b>기타</b></a>
    </div>
</div>

<script>
    function chargePoint1() {
        var logIn = '<%=(String)session.getAttribute("logIn")%>';

        if(!Array.isArray(logIn)){
            document.location.href='/user/logIn.html';
        } else {
            logIn.setPoint = logIn.getPoint() + 1000;
            document.location.href='/user/chargePoint';
        }

    }
</script>
</body>
</body>
</html>